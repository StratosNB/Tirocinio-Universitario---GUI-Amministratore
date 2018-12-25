package admingui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import admingui.db.dao.services.ClientAcPolicyService;
import admingui.db.dao.services.ClientAttributeService;
import admingui.db.dao.services.ClientService;
import admingui.db.dao.services.EnviromentAttributeService;
import admingui.db.dao.services.JavaScriptFunctionService;
import admingui.db.dao.services.ObjectAttributeService;
import admingui.db.dao.services.UserAcPolicyService;
import admingui.db.dao.services.UserAttributeService;
import admingui.db.dao.services.UserService;
import admingui.model.AccessControlPolicy;
import admingui.model.Attribute;
import admingui.model.JavaScriptFunction;
import admingui.view.AcPoliciesPanel;
import admingui.view.dialogs.AddPolicyDialog;
import admingui.view.dialogs.EditPolicyDialog;
import admingui.view.dialogs.SearchPolicyDialog;

public class AcPoliciesPanelController {

	private AcPoliciesPanel policiesPanel;
	private AddPolicyDialog addPolicyDialog;
	private EditPolicyDialog editPolicyDialog;
	private SearchPolicyDialog searchPolicyDialog;
	private JTable policiesSearchResultsTable;
	private RSyntaxTextArea expTextArea;
	private JTextPane expValidationOutput;

	private UserService userService;
	private ClientService clientService;
	private UserAttributeService userAttributeService;
	private ClientAttributeService clientAttributeService;
	private ObjectAttributeService objectAttributeService;
	private EnviromentAttributeService enviromentAttributeService;
	private UserAcPolicyService userAcPolicyService;
	private ClientAcPolicyService clientAcPolicyService;
	private JavaScriptFunctionService jsFunctionService;

	private List<AccessControlPolicy> allPolicies;
	private List<Attribute> allAttributes;
	private List<JavaScriptFunction> allJsFunctions;

	private ScriptEngineManager factory;
	private ScriptEngine engine;

	private int index = 0;

	public AcPoliciesPanelController(AcPoliciesPanel policiesPanel) {
		this.policiesPanel = policiesPanel;
		this.addPolicyDialog = policiesPanel.getAddPolicyDialog();
		this.editPolicyDialog = policiesPanel.getEditPolicyDialog();
		this.searchPolicyDialog = policiesPanel.getSearchPolicyDialog();
		this.policiesSearchResultsTable = searchPolicyDialog.getPoliciesResultTable();
		this.expTextArea = addPolicyDialog.getExpTextArea();
		this.expValidationOutput = addPolicyDialog.getValidationOutputText();

		initServices();
		addListeners();
		initLists();

		factory = new ScriptEngineManager();
		engine = factory.getEngineByName("JavaScript");

		try {
			policiesPanel.setAcPolicyData(allPolicies.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("There are not policies saved in the db");
		}
	}

	protected void initServices() {
		userService = new UserService();
		clientService = new ClientService();
		userAttributeService = new UserAttributeService();
		clientAttributeService = new ClientAttributeService();
		objectAttributeService = new ObjectAttributeService();
		enviromentAttributeService = new EnviromentAttributeService();
		clientAcPolicyService = new ClientAcPolicyService();
		userAcPolicyService = new UserAcPolicyService();
		jsFunctionService = new JavaScriptFunctionService();
	}

	protected void addListeners() {
		policiesPanel.addPoliciesPanelButtonsListener(new PoliciesPanelButtonsListener());
		policiesPanel.addNavigationButtonsListener(new NavigationButtonsListener());
		addPolicyDialog.addButtonsListener(new AddPolicyDialogListener());
		searchPolicyDialog.addButtonsListener(new SearchPolicyDialogListener());
	}

	protected void initLists() {
		allPolicies = new ArrayList<>();
		allAttributes = new ArrayList<>();
		allJsFunctions = new ArrayList<>();
		allPolicies.addAll(userAcPolicyService.getAll());
		allPolicies.addAll(clientAcPolicyService.getAll());
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

				Object result = engine.eval(new java.io.FileReader(file));

				if (result instanceof Boolean) {
					validatioOutput.setText(">> No syntax errors where found");
				} else {
					validatioOutput.setText(">> The parametric predicate is not correct");
					return;
				}

			} catch (FileNotFoundException e1) {
				System.out.println("File not found");
				return;
			} catch (ScriptException e1) {
				validatioOutput.setText(e1.getMessage());
				return;
			} catch (ClassCastException e1) {
				System.out.println("Class Cast is not a problem!");
			}
		}
	}

	public void createAcPolicy(String radioButtonText, AccessControlPolicy newPolicy) {
		if (radioButtonText.equals("Users")) {
			userAcPolicyService.createAcPolicy(newPolicy);
		}
		if (radioButtonText.equals("Clients")) {
			clientAcPolicyService.createAcPolicy(newPolicy);
		}
		allPolicies.add(newPolicy);
		JOptionPane.showMessageDialog(addPolicyDialog.getMainDialog(), "New Policy created with success");
		addPolicyDialog.cleanTexts();
		addPolicyDialog.dispose();
		return;
	}

	public void searchPolicy(String radioButtonName, int selectedId) {
		if (radioButtonName.equals("Users")) {
			userAcPolicyService.setPoliciesInTable(policiesSearchResultsTable, selectedId);
		}
		if (radioButtonName.equals("Clients")) {
			clientAcPolicyService.setPoliciesInTable(policiesSearchResultsTable, selectedId);
		}
		if (policiesSearchResultsTable.getRowCount() == 0) {
			JOptionPane.showMessageDialog(searchPolicyDialog.getMainDialog(), "No results where found");
		}
		return;
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
				addPolicyDialog.installCompletionService(allAttributes, allJsFunctions);
				addPolicyDialog.show();
				break;

			case "OPEN EDIT POLICY DIALOG":
				editPolicyDialog.show();
				break;

			case "OPEN SEARCH POLICY DIALOG":
				searchPolicyDialog.cleanComboBoxs();
				searchPolicyDialog.setComboBoxItems(userService.getAllUsers(), clientService.getAllClients());
				searchPolicyDialog.show();
				break;

			case "DELETE POLICY":
				int id = policiesPanel.getId();
				int option = JOptionPane.showConfirmDialog(policiesPanel.getMainPanel(),
						"Do you want to delete this Policy?", "Delete Warning", JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					userAcPolicyService.deleteBySubjectId(id);
					clientAcPolicyService.deleteBySubjectId(id);
					allPolicies.remove(index);

					JOptionPane.showMessageDialog(addPolicyDialog.getMainDialog(),
							"Policy " + id + " deleted with Success");
				}
				break;

			case "EXPORT POLICIES TO REDIS":
				System.out.println("Export...");
				break;
			}
		}
	}

	class NavigationButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (e.getActionCommand()) {
			case "First":
				index = 0;
				policiesPanel.setAcPolicyData(allPolicies.get(index));
				break;

			case "Previous":
				index--;
				if (index < 0) {
					index = 0;
					JOptionPane.showMessageDialog(policiesPanel.getMainPanel(), "You have reached the beginning");
					return;
				}
				policiesPanel.setAcPolicyData(allPolicies.get(index));
				break;

			case "Next":
				index++;
				if (index == allPolicies.size()) {
					index = allPolicies.size() - 1;
					JOptionPane.showMessageDialog(policiesPanel.getMainPanel(), "The are not more records to show");
					return;
				}
				policiesPanel.setAcPolicyData(allPolicies.get(index));
				break;

			case "Last":
				index = allPolicies.size() - 1;
				policiesPanel.setAcPolicyData(allPolicies.get(index));
				break;
			}
		}
	}

	class AddPolicyDialogListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "RADIO BUTTON 1 SELECTED":
				addPolicyDialog.getClientsIds().setEnabled(false);
				addPolicyDialog.getUsersIds().setEnabled(true);
				break;

			case "RADIO BUTTON 2 SELECTED":
				addPolicyDialog.getUsersIds().setEnabled(false);
				addPolicyDialog.getClientsIds().setEnabled(true);
				break;

			case "ADD DIALOG VALIDATE":
				validateJsFunction(expTextArea, expValidationOutput, "Parametric_Expression.js");
				break;

			case "CREATE AC POLICY":
				if (addPolicyDialog.isInputDataEmpty()) {
					JOptionPane.showMessageDialog(addPolicyDialog.getMainDialog(), "     Some fields are empty");
					return;
				}

				if (!addPolicyDialog.isParametricPredicateCorrect()) {
					JOptionPane.showMessageDialog(addPolicyDialog.getMainDialog(),
							"The Parametric Predicate is not correct, try again");
					return;
				}
				AccessControlPolicy newAcPolicy = addPolicyDialog.createAcPolicy();
				createAcPolicy(searchPolicyDialog.getSelectedRadioButtonText(), newAcPolicy);
				break;

			case "CLOSE ADD POLICY DIALOG":
				addPolicyDialog.dispose();
				break;
			}
		}
	}

	class SearchPolicyDialogListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "RADIO BUTTON 1 SELECTED":
				searchPolicyDialog.getClientIdsCbox().setEnabled(false);
				searchPolicyDialog.getUserIdsCbox().setEnabled(true);
				break;

			case "RADIO BUTTON 2 SELECTED":
				searchPolicyDialog.getUserIdsCbox().setEnabled(false);
				searchPolicyDialog.getClientIdsCbox().setEnabled(true);
				break;

			case "SEARCH POLICY":
				if (!searchPolicyDialog.isIdSelected()) {
					JOptionPane.showMessageDialog(searchPolicyDialog.getMainDialog(), "You must select an Id first");
					return;
				}
				int selectedId = searchPolicyDialog.getSelectedId();
				searchPolicy(searchPolicyDialog.getSelectedRadioButtonText(), selectedId);
				searchPolicyDialog.initFilterTextField();
				break;

			case "CLOSE DIALOG":
				searchPolicyDialog.getMainDialog().dispose();
				break;
			}
		}
	}
}