package admingui.controller;

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

import admingui.db.dao.services.JavaScriptFunctionService;
import admingui.model.Attribute;
import admingui.model.JavaScriptFunction;
import admingui.view.JsFunctionsPanel;
import admingui.view.dialogs.AddJsFunctionDialog;
import admingui.view.dialogs.EditJsFunctionDialog;

public class JavaScriptFunctionsController {

	private JsFunctionsPanel functionsPanel;
	private JList<String> functionsList;
	private DefaultListModel<String> listModel;
	private RSyntaxTextArea jsFunctionsTextArea;
	private JTextPane createFunctionValidationOutput;
	private JTextPane editFunctionValidationOutput;
	private AddJsFunctionDialog addFunctionDialog;
	private EditJsFunctionDialog editFunctionDialog;

	private ScriptEngineManager factory;
	private ScriptEngine engine;

	private JavaScriptFunctionService jsFunctionService;

	private List<Attribute> allAttributes;
	private List<JavaScriptFunction> allJsFunctions;

	public JavaScriptFunctionsController(JsFunctionsPanel functionsPanel) {
		this.functionsPanel = functionsPanel;
		this.functionsList = functionsPanel.getFunctionsList();
		this.jsFunctionsTextArea = functionsPanel.getFunctionsTextArea();
		this.createFunctionValidationOutput = functionsPanel.getCreateFunctionValidationOutput();
		this.editFunctionValidationOutput = functionsPanel.getEditFunctionValidationOutput();
		this.addFunctionDialog = functionsPanel.getAddFunctionDialog();
		this.editFunctionDialog = functionsPanel.getEditFunctionDialog();

		functionsPanel.addJsFunctionsDialogListener(new FunctionsDialogButtonsListener());
		functionsPanel.addFunctionsListClickListener(new FunctionsListClickListener());

		jsFunctionService = new JavaScriptFunctionService();
		functionsPanel.setFunctionsListModel(jsFunctionService.getAll());

		factory = new ScriptEngineManager();
		engine = factory.getEngineByName("JavaScript");

		initLists();
	}

	protected void initLists() {
		allAttributes = new ArrayList<>();
		allJsFunctions = new ArrayList<>();
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

	protected void closeFunctionDialog(JDialog dialog, JTextPane textPane, String msg) {
		JOptionPane.showMessageDialog(dialog, msg);
		dialog.dispose();
		textPane.setText("");
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
				editFunctionDialog.getFunctionsTextArea().setText(functionsPanel.getFunctionsTextArea().getText());
				editFunctionDialog.getMainDialog().setVisible(true);
				break;

			case "DELETE FUNCTION":
				int deleteOption = JOptionPane.showConfirmDialog(functionsPanel.getMainPanel(),
						"Do you want to delete this Function?", "Delete Warning", JOptionPane.YES_NO_OPTION);

				if (deleteOption == 0) {
					listModel.removeElementAt(functionsList.getSelectedIndex());
					jsFunctionService.deleteByName(functionName);
					JOptionPane.showMessageDialog(functionsPanel.getMainPanel(), "Function Deleted Successfully");
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
				/*expEditor.installCompletionService(allAttributes, allJsFunctions);
				functionsDialog.getMainDialog().dispose();*/
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
