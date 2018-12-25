package admingui.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.model.JavaScriptFunction;
import net.miginfocom.swing.MigLayout;

public class EditJsFunctionDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;
	
	private RTextScrollPane sp1;
	private JScrollPane sp2;
	private RSyntaxTextArea functionsTextArea;
	private JTextPane functionValidationOutput;
	private JLabel validationOutput;
	private JButton validateFunction;
	private JButton ok;

	public EditJsFunctionDialog() {
		initComponents();
	}

	protected void initComponents() {
		validationOutput = new JLabel("Validation Output:");
		initButtons();
		initTextPanes();
		initMainPanel();
		initMainDialog();
	}

	protected void initTextPanes() {
		functionsTextArea = new RSyntaxTextArea(10, 20);
		functionsTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		functionValidationOutput = new JTextPane();
		sp1 = new RTextScrollPane(functionsTextArea);
		sp2 = new JScrollPane(functionValidationOutput);
	}

	protected void initButtons() {
		validateFunction = new JButton("Validate");
		ok = new JButton("Ok");
		validateFunction.setActionCommand("EDIT VALIDATE FUNCTION");
		ok.setActionCommand("FINISH FUNCTION EDITION");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "[]", "[][][][]"));
		mainPanel.add(sp1, "cell 0 0,grow");
		mainPanel.add(validationOutput, "cell 0 1");
		mainPanel.add(sp2, "cell 0 2,grow");
		mainPanel.add(validateFunction, "cell 0 2,alignx left,aligny center");
		mainPanel.add(ok, "cell 0 3,alignx right,aligny bottom");
	}

	private void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Edit a Function");
		mainDialog.setBounds(450, 160, 430, 400);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
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

	public JDialog getMainDialog() {
		return mainDialog;
	}

	public RSyntaxTextArea getFunctionsTextArea() {
		return functionsTextArea;
	}

	public JTextPane getFunctionValidationOutput() {
		return functionValidationOutput;
	}

	public JButton getValidateFunction() {
		return validateFunction;
	}

	public JButton getOk() {
		return ok;
	}
}