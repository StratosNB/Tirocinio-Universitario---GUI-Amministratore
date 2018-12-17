package admingui.views.policies.dialogs;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
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
import org.fife.ui.rsyntaxtextarea.spell.SpellingParser;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.models.Attribute;
import admingui.models.JavaScriptFunction;
import admingui.views.policies.jsfunctions.dialogs.JsFunctionsDialog;
import net.miginfocom.swing.MigLayout;

public class ParametricExpressionEditor {

	private JPanel mainPanel;

	private JLabel validationOutputLbl;

	private JButton functions;
	private JButton validate;

	private RTextScrollPane sp1;

	private RSyntaxTextArea expTextArea;
	private JTextPane validationOutput;

	private JsFunctionsDialog functionsDialog;

	public ParametricExpressionEditor() {
		try {
			initComponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void initComponents() throws IOException {
		functionsDialog = new JsFunctionsDialog();
		initTextAreas();
		initLabels();
		initButtons();
		initMainPanel();
	}

	protected void initTextAreas() throws IOException {
		expTextArea = new RSyntaxTextArea(1, 20);
		expTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);

		sp1 = new RTextScrollPane(expTextArea);

		validationOutput = new JTextPane();
		validationOutput.setEditable(false);

		File zip = new File("english_dic.zip");
		boolean usEnglish = true;
		SpellingParser parser = SpellingParser.createEnglishSpellingParser(zip, usEnglish);
		expTextArea.addParser(parser);

		/*
		 * JavaScriptLanguageSupport languageSupport = new
		 * JavaScriptLanguageSupport(); JavaScriptParser javaParser = new
		 * JavaScriptParser(languageSupport, expTextArea);
		 * 
		 * expTextArea.addParser(javaParser);
		 * 
		 * languageSupport.install(expTextArea);
		 */
	}

	public void installCompletionService(List<Attribute> attributes, List<JavaScriptFunction> functions) {
		AutoCompletion ac = new AutoCompletion(createCompletionProvider(attributes, functions));
		ac.install(expTextArea);
	}

	protected void initLabels() {
		validationOutputLbl = new JLabel("Validation Output:");
	}

	protected void initButtons() {
		functions = new JButton("Functions...");
		validate = new JButton("Validate");

		functions.setActionCommand("OPEN FUNCTIONS DIALOG");
		validate.setActionCommand("VALIDATE PARAMETRIC PREDICATE");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "[][]", "[][][][]"));
		mainPanel.add(sp1, "cell 0 0 2 1,growx");
		mainPanel.add(validationOutputLbl, "cell 0 2");
		mainPanel.add(functions, "cell 1 1,alignx right,aligny top");
		mainPanel.add(new JScrollPane(validationOutput), "cell 0 3,grow");
		mainPanel.add(validate, "cell 1 3,alignx left,aligny center");
	}

	private CompletionProvider createCompletionProvider(List<Attribute> attributes,
			List<JavaScriptFunction> functions) {

		DefaultCompletionProvider provider = new DefaultCompletionProvider();

		attributes.forEach(attribute -> provider.addCompletion(new BasicCompletion(provider, attribute.getName())));
		functions.forEach(function -> provider.addCompletion(new BasicCompletion(provider, function.getName())));

		provider.addCompletion(new BasicCompletion(provider, "var"));
		provider.addCompletion(new BasicCompletion(provider, "boolean"));
		provider.addCompletion(new BasicCompletion(provider, "true"));
		provider.addCompletion(new BasicCompletion(provider, "false"));
		provider.addCompletion(new BasicCompletion(provider, "while"));
		provider.addCompletion(new BasicCompletion(provider, "abstract"));
		provider.addCompletion(new BasicCompletion(provider, "break"));
		provider.addCompletion(new BasicCompletion(provider, "for"));
		provider.addCompletion(new BasicCompletion(provider, "case"));

		return provider;
	}

	public void addParametricExpressionEditorButtonsListener(ActionListener e) {
		functions.addActionListener(e);
		validate.addActionListener(e);
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public RSyntaxTextArea getExpTextArea() {
		return expTextArea;
	}

	public JTextPane getValidationOutput() {
		return validationOutput;
	}

	public JsFunctionsDialog getFunctionsDialog() {
		return functionsDialog;
	}
}