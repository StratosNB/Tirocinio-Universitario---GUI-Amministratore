package admingui.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.model.JavaScriptFunction;
import admingui.view.dialogs.AddJsFunctionDialog;
import admingui.view.dialogs.EditJsFunctionDialog;
import net.miginfocom.swing.MigLayout;

public class JsFunctionsPanel {

	private JPanel mainPanel;

	private JSplitPane splitPane;

	private JLabel functions;

	private RTextScrollPane sp1;
	private JScrollPane sp2;

	private RSyntaxTextArea functionsTextArea;

	private JList<String> functionsList;

	private JButton addFunction;
	private JButton editFunction;
	private JButton deleteFunction;

	private AddJsFunctionDialog addFunctionDialog;
	private EditJsFunctionDialog editFunctionDialog;

	public JsFunctionsPanel() {
		initComponents();
	}

	protected void initComponents() {
		addFunctionDialog = new AddJsFunctionDialog();
		editFunctionDialog = new EditJsFunctionDialog();

		functions = new JLabel("Functions:");
		functionsList = new JList<String>();
		sp2 = new JScrollPane(functionsList);

		initButtons();
		initTextAreas();
		initSplitPane();
		initMainPanel();
	}

	protected void initButtons() {
		addFunction = new JButton("Add...");
		editFunction = new JButton("Edit...");
		deleteFunction = new JButton("Delete");
		setActionCommands();
	}

	protected void initTextAreas() {
		functionsTextArea = new RSyntaxTextArea(8, 30);
		functionsTextArea.setEditable(false);
		functionsTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		sp1 = new RTextScrollPane(functionsTextArea);
	}

	protected void initSplitPane() {
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp2, sp1);
		splitPane.setDividerLocation(115);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "[]", "[][][]"));
		mainPanel.add(functions, "cell 0 0,aligny bottom");
		mainPanel.add(splitPane, "cell 0 1,grow");
		mainPanel.add(addFunction, "cell 0 2,aligny top");
		mainPanel.add(editFunction, "cell 0 2,aligny top");
		mainPanel.add(deleteFunction, "cell 0 2,aligny top");
	}

	protected void setActionCommands() {
		addFunction.setActionCommand("OPEN CREATE FUNCTION");
		editFunction.setActionCommand("OPEN EDIT FUNCTION");
		deleteFunction.setActionCommand("DELETE FUNCTION");
	}

	public void addJsFunctionsDialogListener(ActionListener e) {
		addFunction.addActionListener(e);
		editFunction.addActionListener(e);
		deleteFunction.addActionListener(e);
		addFunctionDialog.getValidateFunction().addActionListener(e);
		editFunctionDialog.getValidateFunction().addActionListener(e);
		addFunctionDialog.getOk().addActionListener(e);
		editFunctionDialog.getOk().addActionListener(e);
	}

	public void addFunctionsListClickListener(MouseListener e1) {
		functionsList.addMouseListener(e1);
	}

	public JavaScriptFunction createJsFunction() {
		JavaScriptFunction function = new JavaScriptFunction();
		function.setName(createFunctionName());
		function.setCode(functionsTextArea.getText());
		return function;
	}

	public String createFunctionName() {
		String code = functionsTextArea.getText();
		int endIndex = code.indexOf("{");
		String name = code.substring(9, endIndex);
		return name;
	}

	public void setFunctionsListModel(List<JavaScriptFunction> functions) {
		DefaultListModel<String> model = new DefaultListModel<>();
		functions.forEach(function -> model.addElement(function.getName()));
		functionsList.setModel(model);
	}

	public AddJsFunctionDialog getAddFunctionDialog() {
		return addFunctionDialog;
	}

	public EditJsFunctionDialog getEditFunctionDialog() {
		return editFunctionDialog;
	}

	public RSyntaxTextArea getFunctionsTextArea() {
		return this.functionsTextArea;
	}

	public JTextPane getCreateFunctionValidationOutput() {
		return this.addFunctionDialog.getFunctionValidationOutput();
	}

	public JTextPane getEditFunctionValidationOutput() {
		return this.editFunctionDialog.getFunctionValidationOutput();
	}

	public JList<String> getFunctionsList() {
		return this.functionsList;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}