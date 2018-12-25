package admingui.view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.model.Attribute;
import admingui.model.JavaScriptFunction;
import net.miginfocom.swing.MigLayout;

public class AddJsFunctionDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;
	private RTextScrollPane sp1;
	private JScrollPane sp2;
	private RSyntaxTextArea functionsTextArea;
	private JTextPane functionValidationOutput;
	private JLabel validationOutput;
	private JButton validateFunction;
	private JButton ok;

	public AddJsFunctionDialog() {
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
		functionValidationOutput.setEditable(false);
		functionValidationOutput.setBackground(Color.LIGHT_GRAY);
		functionValidationOutput.setPreferredSize(new Dimension(300, 50));
		functionValidationOutput.setBackground(Color.LIGHT_GRAY);
		sp1 = new RTextScrollPane(functionsTextArea);
		sp2 = new JScrollPane(functionValidationOutput);
	}

	protected void initButtons() {
		validateFunction = new JButton("Validate");
		ok = new JButton("Ok");
		validateFunction.setActionCommand("CREATE VALIDATE FUNCTION");
		ok.setActionCommand("CREATE FUNCTION");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "[]", "[][][][]"));
		mainPanel.add(sp1, "cell 0 0,grow");
		mainPanel.add(validationOutput, "cell 0 1");
		mainPanel.add(sp2, "cell 0 2,grow");
		mainPanel.add(validateFunction, "cell 0 2,alignx left,aligny center");
		mainPanel.add(ok, "cell 0 3,alignx right,aligny bottom");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Create a Function");
		mainDialog.setBounds(450, 160, 430, 400);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	private CompletionProvider createCompletionProvider(List<Attribute> attributes) {

		DefaultCompletionProvider provider = new DefaultCompletionProvider();

		attributes.forEach(attribute -> provider.addCompletion(new BasicCompletion(provider, attribute.getName())));

		provider.addCompletion(new BasicCompletion(provider, "var"));
		provider.addCompletion(new BasicCompletion(provider, "boolean"));
		provider.addCompletion(new BasicCompletion(provider, "true"));
		provider.addCompletion(new BasicCompletion(provider, "false"));
		provider.addCompletion(new BasicCompletion(provider, "while"));
		provider.addCompletion(new BasicCompletion(provider, "abstract"));
		provider.addCompletion(new BasicCompletion(provider, "break"));
		provider.addCompletion(new BasicCompletion(provider, "for"));
		provider.addCompletion(new BasicCompletion(provider, "case"));
		provider.addCompletion(new BasicCompletion(provider, "functions"));

		return provider;
	}

	public JavaScriptFunction createJsFunction() {
		JavaScriptFunction function = new JavaScriptFunction();
		function.setName(createFunctionName());
		function.setCode(functionsTextArea.getText());
		return function;
	}

	public void installCompletionService(List<Attribute> attributes) {
		AutoCompletion ac = new AutoCompletion(createCompletionProvider(attributes));
		ac.install(functionsTextArea);
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