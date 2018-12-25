package admingui.view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.js.JavaScriptCompletionProvider;
import org.fife.rsta.ac.js.JavaScriptLanguageSupport;
import org.fife.rsta.ac.js.JavaScriptParser;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.JavaScriptVariableDeclaration;
import org.fife.rsta.ac.js.ast.VariableResolver;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.spell.SpellingParser;
import org.fife.ui.rtextarea.RTextScrollPane;

import admingui.model.AccessControlPolicy;
import admingui.model.Attribute;
import admingui.model.Client;
import admingui.model.JavaScriptFunction;
import admingui.model.User;
import admingui.utils.AdminGuiConstants;
import net.miginfocom.swing.MigLayout;

public class AddPolicyDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JLabel idLbl;
	private JLabel topicFilterExpressionLbl;
	private JLabel parametricPredicateLbl;
	private JLabel validationOutputLbl;
	private JLabel permissionLbl;
	private JTextField topicFilterExpressionInput;
	private RTextScrollPane sp1;
	private RSyntaxTextArea expTextArea;
	private JTextPane validationOutputText;
	private JComboBox<String> usersIds;
	private JComboBox<String> clientsIds;
	private JComboBox<String> permissions;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private ButtonGroup buttonGroup;
	private JButton validate;
	private JButton okButton;
	private JButton cancelButton;

	public AddPolicyDialog() {
		try {
			initComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void initComponents() throws IOException {
		initButtons();
		initLabels();
		initComboBoxes();
		initTextFields();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		radioButton1 = new JRadioButton("Users: ");
		radioButton2 = new JRadioButton("Clients:");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);

		validate = new JButton("Validate");
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		setActionCommands();
	}

	protected void initLabels() {
		idLbl = new JLabel("ID:");
		topicFilterExpressionLbl = new JLabel("Topic Filter Expression:");
		parametricPredicateLbl = new JLabel("Parametric Predicate:");
		validationOutputLbl = new JLabel("Validation Output:");
		permissionLbl = new JLabel("Permission:");
	}

	protected void initComboBoxes() {
		usersIds = new JComboBox<>();
		clientsIds = new JComboBox<>();
		permissions = new JComboBox<>(AdminGuiConstants.PERMISSIONS);

		usersIds.setEnabled(false);
		clientsIds.setEnabled(false);
	}

	protected void initTextFields() throws IOException {
		topicFilterExpressionInput = new JTextField(40);

		expTextArea = new RSyntaxTextArea(1, 20);
		expTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		sp1 = new RTextScrollPane(expTextArea);

		validationOutputText = new JTextPane();
		validationOutputText.setEditable(false);
		validationOutputText.setPreferredSize(new Dimension(300, 50));
		validationOutputText.setBackground(Color.LIGHT_GRAY);

		File zip = new File("english_dic.zip");
		boolean usEnglish = true;
		SpellingParser parser = SpellingParser.createEnglishSpellingParser(zip, usEnglish);
		expTextArea.addParser(parser);

	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("fill", "15[]15", "15[][][]20[][]20[]20[][]push[]"));
		mainPanel.add(idLbl, "cell 0 0");
		mainPanel.add(radioButton1, "cell 0 1");
		mainPanel.add(usersIds, "cell 0 1");
		mainPanel.add(radioButton2, "cell 0 2");
		mainPanel.add(clientsIds, "cell 0 2");
		mainPanel.add(topicFilterExpressionLbl, "cell 0 3");
		mainPanel.add(topicFilterExpressionInput, "cell 0 4");
		mainPanel.add(createExpEditingPanel(), "cell 0 5,grow");
		mainPanel.add(permissionLbl, "cell 0 6");
		mainPanel.add(permissions, "cell 0 7");
		mainPanel.add(okButton, "cell 0 8,right");
		mainPanel.add(cancelButton, "cell 0 8,right");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Add an Access Control Policy");
		mainDialog.setBounds(450, 160, 350, 450);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected void setActionCommands() {
		radioButton1.setActionCommand("RADIO BUTTON 1 SELECTED");
		radioButton2.setActionCommand("RADIO BUTTON 2 SELECTED");
		validate.setActionCommand("ADD DIALOG VALIDATE");
		okButton.setActionCommand("CREATE AC POLICY");
		cancelButton.setActionCommand("CLOSE ADD POLICY DIALOG");
	}

	public JPanel createExpEditingPanel() {
		JPanel panel = new JPanel(new MigLayout("fill", "0[]0", "[][][][]"));
		panel.add(parametricPredicateLbl, "cell 0 0");
		panel.add(sp1, "cell 0 1,grow");
		panel.add(validationOutputLbl, "cell 0 2");
		panel.add(new JScrollPane(validationOutputText), "cell 0 3 ,grow");
		panel.add(validate, "cell 0 3 ,alignx center,aligny center");
		return panel;
	}

	public void installCompletionService(List<Attribute> attributes, List<JavaScriptFunction> functions) {
		AutoCompletion ac = new AutoCompletion(createCompletionProvider(attributes, functions));
		ac.install(expTextArea);
		/*
		 * JavaScriptLanguageSupport languageSupport = new
		 * JavaScriptLanguageSupport(); JavaScriptCompletionProvider
		 * completionProvider = new JavaScriptCompletionProvider(new
		 * JarManager(),languageSupport);
		 * completionProvider.setStringCompletionProvider(completionProvider);
		 * 
		 * completionProvider
		 * 
		 * CodeBlock c = new CodeBlock(1);
		 * 
		 * JavaScriptParser javaParser = new JavaScriptParser(languageSupport,
		 * expTextArea); JavaScriptVariableDeclaration subject = new
		 * JavaScriptVariableDeclaration("s", 1, (SourceCompletionProvider)
		 * ac.getCompletionProvider(), new CodeBlock(1));
		 * 
		 * VariableResolver vr = new VariableResolver();
		 * vr.addLocalVariable(subject);
		 * 
		 * javaParser.setVariablesAndFunctions(vr);
		 * 
		 * expTextArea.addParser(javaParser);
		 * 
		 * languageSupport.install(expTextArea);
		 */
	}

	protected CompletionProvider createCompletionProvider(List<Attribute> attributes,
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

		provider.addCompletion(new ShorthandCompletion(provider, "s.", "Name", "Name"));

		return provider;
	}

	public void setComboBoxItems(List<User> users, List<Client> clients) {
		usersIds.addItem("");
		clientsIds.addItem("");
		users.forEach(user -> usersIds.addItem(String.valueOf(user.getId())));
		clients.forEach(client -> clientsIds.addItem(String.valueOf(client.getId())));
	}

	public void cleanComboBoxs() {
		usersIds.removeAllItems();
		clientsIds.removeAllItems();
	}

	public AccessControlPolicy createAcPolicy() {
		AccessControlPolicy acPolicy = new AccessControlPolicy();
		acPolicy.setId(Integer.valueOf(getEnabledJComboBox().getSelectedItem().toString()));
		acPolicy.setTopicFilterExpression(topicFilterExpressionInput.getText());
		acPolicy.setParemetricPredicate(expTextArea.getText());
		acPolicy.setPermission(permissions.getSelectedItem().toString());
		return acPolicy;
	}

	public JComboBox<String> getEnabledJComboBox() {
		if (usersIds.isEnabled()) {
			return usersIds;
		}
		if (clientsIds.isEnabled()) {
			return clientsIds;
		}
		return null;
	}

	public boolean isInputDataEmpty() {
		return getEnabledJComboBox() == null || getEnabledJComboBox().getSelectedItem().toString().isEmpty()
				|| topicFilterExpressionInput.getText().isEmpty() || expTextArea.getText().isEmpty()
				|| permissions.getSelectedItem().toString().isEmpty();
	}

	public boolean isParametricPredicateCorrect() {
		if (validationOutputText.getText().equals(">> No syntax errors where found")) {
			return true;
		}
		return false;
	}

	public void addButtonsListener(ActionListener e) {
		radioButton1.addActionListener(e);
		radioButton2.addActionListener(e);
		validate.addActionListener(e);
		cancelButton.addActionListener(e);
		okButton.addActionListener(e);
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void cleanTexts() {
		radioButton1.setSelected(false);
		radioButton2.setSelected(false);
		usersIds.setSelectedItem("");
		clientsIds.setSelectedItem("");
		topicFilterExpressionInput.setText("");
		expTextArea.setText("");
		validationOutputText.setText("");
		permissions.setSelectedItem("");
	}

	public void dispose() {
		mainDialog.dispose();
	}

	public JDialog getMainDialog() {
		return mainDialog;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JRadioButton getRadioButton1() {
		return radioButton1;
	}

	public JRadioButton getRadioButton2() {
		return radioButton2;
	}

	public JComboBox<String> getUsersIds() {
		return usersIds;
	}

	public JComboBox<String> getClientsIds() {
		return clientsIds;
	}

	public JTextPane getValidationOutputText() {
		return validationOutputText;
	}

	public void setValidationOutputText(JTextPane validationOutputText) {
		this.validationOutputText = validationOutputText;
	}

	public RSyntaxTextArea getExpTextArea() {
		return expTextArea;
	}

	public JButton getFinish() {
		return okButton;
	}

	public JButton getCancel() {
		return cancelButton;
	}
}