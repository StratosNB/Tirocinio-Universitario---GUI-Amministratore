package admingui.controllers.policies;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import admingui.db.services.ClientAttributeService;
import admingui.db.services.ClientService;
import admingui.db.services.EnviromentAttributeService;
import admingui.db.services.JavaScriptFunctionService;
import admingui.db.services.ObjectAttributeService;
import admingui.db.services.UserAttributeService;
import admingui.db.services.UserService;
import admingui.models.Attribute;
import admingui.models.JavaScriptFunction;
import admingui.views.policies.AcPoliciesPanel;
import admingui.views.policies.dialogs.AddPolicyDialog;
import admingui.views.policies.dialogs.ParametricExpressionEditor;
import admingui.views.policies.jsfunctions.dialogs.AddJsFunctionDialog;
import admingui.views.policies.jsfunctions.dialogs.EditJsFunctionDialog;
import admingui.views.policies.jsfunctions.dialogs.JsFunctionsDialog;

public class AcPoliciesPanelController {

	private AcPoliciesPanel policiesPanel;
	private AddPolicyDialog addPolicyDialog;
	private CardLayout cl;
	private ParametricExpressionEditor editor;
	private JsFunctionsDialog functionsDialog;
	private RSyntaxTextArea expTextArea;
	private RSyntaxTextArea jsFunctionsTextArea;
	private JTextPane expValidationOutput;
	private JTextPane createFunctionValidationOutput;
	private JTextPane editFunctionValidationOutput;
	private AddJsFunctionDialog addFunctionDialog;
	private EditJsFunctionDialog editFunctionDialog;

	private JList<String> functionsList;
	private DefaultListModel<String> listModel;

	private ScriptEngineManager factory;
	private ScriptEngine engine;

	private UserService userService;
	private ClientService clientService;
	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;
	private ObjectAttributeService objectAttributeService;
	private EnviromentAttributeService enviromentAttributeService;
	private JavaScriptFunctionService jsFunctionService;

	private List<Attribute> allAttributes;
	private List<JavaScriptFunction> allJsFunctions;

	public AcPoliciesPanelController(AcPoliciesPanel policiesPanel) {
		this.policiesPanel = policiesPanel;
		this.addPolicyDialog = policiesPanel.getAddPolicyDialog();
		this.cl = (CardLayout) (addPolicyDialog.getMainPanel().getLayout());
		this.editor = addPolicyDialog.getParametricExpressionEditor();
		this.functionsDialog = editor.getFunctionsDialog();
		this.functionsList = functionsDialog.getFunctionsList();
		this.expTextArea = editor.getExpTextArea();
		this.jsFunctionsTextArea = functionsDialog.getFunctionsTextArea();
		this.expValidationOutput = editor.getValidationOutput();
		this.createFunctionValidationOutput = functionsDialog.getCreateFunctionValidationOutput();
		this.editFunctionValidationOutput = functionsDialog.getEditFunctionValidationOutput();
		this.addFunctionDialog = functionsDialog.getAddFunctionDialog();
		this.editFunctionDialog = functionsDialog.getEditFunctionDialog();

		initServices();
		addListeners();
		initLists();

		factory = new ScriptEngineManager();
		engine = factory.getEngineByName("JavaScript");

		functionsDialog.setFunctionsListModel(jsFunctionService.getAll());
	}

	protected void initServices() {
		userService = new UserService();
		clientService = new ClientService();
		userAttributeService = new UserAttributeService();
		clientAttributeService = new ClientAttributeService();
		objectAttributeService = new ObjectAttributeService();
		enviromentAttributeService = new EnviromentAttributeService();
		jsFunctionService = new JavaScriptFunctionService();
	}

	protected void addListeners() {
		policiesPanel.addPoliciesPanelButtonsListener(new PoliciesPanelButtonsListener());
		editor.addParametricExpressionEditorButtonsListener(new ParametricExpressionEditorButtonsListener());
		addPolicyDialog.addAddPolicyDialogButtonsListener(new AddPolicyDialogButtonsListener());
		functionsDialog.addJsFunctionsDialogListener(new FunctionsDialogButtonsListener());
		functionsDialog.addFunctionsListClickListener(new FunctionsListClickListener());
	}

	protected void initLists() {
		allAttributes = new ArrayList<>();
		allJsFunctions = new ArrayList<>();
	}

	protected void createJsFile(String fileName, RSyntaxTextArea textArea, boolean functions) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			if (!functions) {
				writer.println(textArea.getText());
			} else {
				allJsFunctions.forEach(function -> writer.println(function.getCode()));
				writer.println(textArea.getText());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected void validateJsFunction(RSyntaxTextArea textArea, JTextPane validatioOutput, String file) {
		if (!textArea.getText().isEmpty()) {
			validatioOutput.setText("");
			if (file.equals("JS_Functions.js")) {
				createJsFile(file, textArea, false);
			} else {
				createJsFile(file, textArea, true);
			}
			try {
				validatioOutput.setText((String) engine.eval(new java.io.FileReader(file)));
			} catch (FileNotFoundException e1) {
				System.out.println("File not found");
				return;
			} catch (ScriptException e1) {
				validatioOutput.setText(e1.getMessage());
				return;
			} catch (ClassCastException e1) {
				System.out.println("Class Cast is not a problem!");
			}
			validatioOutput.setText("No syntax errors where found");
		}
	}

	protected boolean isFunctionValid(JTextPane textPane) {
		if (textPane.getText().isEmpty()) {
			JOptionPane.showMessageDialog(editFunctionDialog.getMainDialog(), "The Function must be validated first");
			return false;
		}

		if (!textPane.getText().equals("No syntax errors where found")) {
			JOptionPane.showMessageDialog(editFunctionDialog.getMainDialog(),
					"The function is not valid, please check the syntax and try again");
			return false;
		}
		return true;
	}

	protected void closeFunctionDialog(JDialog dialog, JTextPane textPane, String msg) {
		JOptionPane.showMessageDialog(dialog, msg);
		dialog.dispose();
		textPane.setText("");
	}

	class PoliciesPanelButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			allAttributes = userAttributeService.getAllUserAttributes();
			allAttributes.addAll(clientAttributeService.getAllClientAttributes());
			allAttributes.addAll(objectAttributeService.getAllAttributes());
			allAttributes.addAll(enviromentAttributeService.getAllAttributes());

			allJsFunctions = jsFunctionService.getAll();

			switch (e.getActionCommand()) {
			case "OPEN ADD POLICY DIALOG":
				addPolicyDialog.cleanComboBoxs();
				addPolicyDialog.setComboBoxItems(userService.getAllUsers(), clientService.getAllClients());
				editor.installCompletionService(allAttributes, allJsFunctions);
				cl.first(addPolicyDialog.getMainPanel());
				addPolicyDialog.show();
				break;

			case "OPEN EDIT POLICY DIALOG":
				policiesPanel.getEditPolicyDialog().show();
				break;

			case "OPEN SEARCH POLICY DIALOG":

				break;

			case "DELETE POLICY":

				break;

			case "EXPORT POLICIES TO REDIS":
				System.out.println("Export...");
				break;
			}

		}

	}

	class AddPolicyDialogButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "CANCEL":
				addPolicyDialog.getMainDialog().dispose();
				break;

			case "BACK":
				cl.previous(addPolicyDialog.getMainPanel());
				break;

			case "NEXT":
				cl.next(addPolicyDialog.getMainPanel());
				break;

			case "FINISH":
				addPolicyDialog.dispose();
				break;
			}
		}
	}

	class ParametricExpressionEditorButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "OPEN FUNCTIONS DIALOG":
				functionsDialog.getMainDialog().setVisible(true);
				break;

			case "VALIDATE PARAMETRIC PREDICATE":
				validateJsFunction(expTextArea, expValidationOutput, "Parametric_Expression.js");
				break;

			}
		}

	}

	class FunctionsDialogButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			listModel = (DefaultListModel<String>) functionsList.getModel();
			String functionName = functionsList.getSelectedValue();

			switch (e.getActionCommand()) {

			case "OPEN CREATE FUNCTION":
				addFunctionDialog.installCompletionService(allAttributes);
				addFunctionDialog.getMainDialog().setVisible(true);
				break;

			case "OPEN EDIT FUNCTION":
				editFunctionDialog.installCompletionService(allAttributes);
				editFunctionDialog.getFunctionsTextArea().setText(functionsDialog.getFunctionsTextArea().getText());
				editFunctionDialog.getMainDialog().setVisible(true);
				break;

			case "DELETE FUNCTION":
				int deleteOption = JOptionPane.showConfirmDialog(functionsDialog.getMainDialog(),
						"Do you want to delete this Function?", "Delete Warning", JOptionPane.YES_NO_OPTION);

				if (deleteOption == 0) {
					listModel.removeElementAt(functionsList.getSelectedIndex());
					jsFunctionService.deleteByName(functionName);
					JOptionPane.showMessageDialog(functionsDialog.getMainDialog(), "Function Deleted Successfully");
				}
				break;

			case "CREATE VALIDATE FUNCTION":
				validateJsFunction(addFunctionDialog.getFunctionsTextArea(), createFunctionValidationOutput,
						"JS_Functions.js");
				break;

			case "EDIT VALIDATE FUNCTION":
				validateJsFunction(editFunctionDialog.getFunctionsTextArea(), editFunctionValidationOutput,
						"JS_Functions.js");
				break;

			case "DISPOSE FUNCTIONS DIALOG":
				allJsFunctions = jsFunctionService.getAll();
				editor.installCompletionService(allAttributes, allJsFunctions);
				functionsDialog.getMainDialog().dispose();
				break;

			case "CREATE FUNCTION":
				if (!isFunctionValid(createFunctionValidationOutput)) {
					return;
				} else {
					if (createFunctionValidationOutput.getText().equals("No syntax errors where found")) {
						jsFunctionService.create(addFunctionDialog.createJsFunction());
						listModel.addElement(addFunctionDialog.createFunctionName());
						closeFunctionDialog(addFunctionDialog.getMainDialog(), createFunctionValidationOutput,
								"New Function created with success");
					}
				}
				break;

			case "FINISH FUNCTION EDITION":
				if (!isFunctionValid(editFunctionValidationOutput)) {
					return;
				} else {
					if (editFunctionValidationOutput.getText().equals("No syntax errors where found")) {
						jsFunctionService.update(editFunctionDialog.createJsFunction(), functionName);
						listModel.setElementAt(editFunctionDialog.createJsFunction().getName(),
								functionsList.getSelectedIndex());
						closeFunctionDialog(editFunctionDialog.getMainDialog(), editFunctionValidationOutput,
								"Function Updated with Success");
					}
				}

				break;
			}
		}
	}

	class FunctionsListClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			listModel = (DefaultListModel<String>) functionsList.getModel();
			String functionName = (String) listModel.getElementAt(functionsList.locationToIndex(e.getPoint()));
			JavaScriptFunction function = jsFunctionService.getFunctionByName(functionName);
			jsFunctionsTextArea.setText(function.getCode());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}