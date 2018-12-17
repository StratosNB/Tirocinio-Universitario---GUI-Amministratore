package admingui.views.policies.jsfunctions.dialogs;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.models.JavaScriptFunction;
import net.miginfocom.swing.MigLayout;

public class JsFunctionsDialog {

	private JDialog mainDialog;
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
	private JButton closeDialog;

	private AddJsFunctionDialog addFunctionDialog;
	private EditJsFunctionDialog editFunctionDialog;

	public JsFunctionsDialog() {
		initComponents();
	}

	protected void initComponents() {
		initDialogs();
		initList();
		initButtons();
		initTextAreas();
		initSplitPane();
		initMainPanel();
		initMainDialog();
	}

	protected void initDialogs() {
		addFunctionDialog = new AddJsFunctionDialog();
		editFunctionDialog = new EditJsFunctionDialog();
	}

	protected void initList() {
		functions = new JLabel("Functions:");
		functionsList = new JList<String>();
		sp2 = new JScrollPane(functionsList);
	}

	protected void initButtons() {
		addFunction = new JButton("Add...");
		editFunction = new JButton("Edit...");
		deleteFunction = new JButton("Delete");
		closeDialog = new JButton("Close");
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
		mainPanel = new JPanel(new MigLayout("fill", "[]", "[][][][]"));
		mainPanel.add(functions, "cell 0 0,aligny bottom");
		mainPanel.add(splitPane, "cell 0 1,grow");
		mainPanel.add(addFunction, "cell 0 2,aligny top");
		mainPanel.add(editFunction, "cell 0 2,aligny top");
		mainPanel.add(deleteFunction, "cell 0 2,aligny top");
		mainPanel.add(closeDialog, "cell 0 3,alignx right,aligny bottom");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("JavaScript Functions Manager");
		mainDialog.setBounds(450, 160, 430, 400);
		mainDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected void setActionCommands() {
		addFunction.setActionCommand("OPEN CREATE FUNCTION");
		editFunction.setActionCommand("OPEN EDIT FUNCTION");
		deleteFunction.setActionCommand("DELETE FUNCTION");
		closeDialog.setActionCommand("DISPOSE FUNCTIONS DIALOG");
	}

	public void addJsFunctionsDialogListener(ActionListener e) {
		addFunction.addActionListener(e);
		editFunction.addActionListener(e);
		deleteFunction.addActionListener(e);
		addFunctionDialog.getValidateFunction().addActionListener(e);
		editFunctionDialog.getValidateFunction().addActionListener(e);
		addFunctionDialog.getOk().addActionListener(e);
		editFunctionDialog.getOk().addActionListener(e);
		closeDialog.addActionListener(e);
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

	public JDialog getMainDialog() {
		return this.mainDialog;
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
}