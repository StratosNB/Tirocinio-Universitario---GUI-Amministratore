package admingui.views.policies.dialogs;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admingui.models.AccessControlPolicy;
import admingui.models.Client;
import admingui.models.User;
import admingui.utils.AdminGuiComponentsConstants;
import net.miginfocom.swing.MigLayout;

public class AddPolicyDialog {

	private final String ID_SELECTION_PANEL = "Id selection panel";
	private final String TF_SPECIFICATION_PANEL = "Tf specification panel";
	private final String EXP_SPECIFICATION_PANEL = "Exp specification panel";
	private final String PR_SELECTION_PANEL = "Pr selection panel";

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JLabel selectId;
	private JLabel specifyTopicFilterExpression;
	private JLabel specifyParametricPredicate;
	private JLabel selectOperation;

	private JLabel userId;
	private JLabel clientId;
	private JLabel parametricPredicate;

	private JComboBox<String> usersIds;
	private JComboBox<String> clientsIds;
	private JComboBox<String> permissions;

	private JTextField topicFilterExpressionInput;

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	private ParametricExpressionEditor editor;

	private JButton cancel;
	private JButton back;
	private JButton back1;
	private JButton back2;
	private JButton next;
	private JButton next1;
	private JButton next2;
	private JButton finish;

	public AddPolicyDialog() {
		initComponents();
	}

	protected void initComponents() {
		editor = new ParametricExpressionEditor();
		initButtons();
		initLabels();
		initComboBoxes();
		initTextFields();
		initPanels();
		initMainPanel();
		initMainDialog();
	}

	protected void initButtons() {
		back = new JButton("Back");
		back1 = new JButton("Back");
		back2 = new JButton("Back");
		next = new JButton("Next");
		next1 = new JButton("Next");
		next2 = new JButton("Next");
		finish = new JButton("Finish");
		cancel = new JButton("Cancel");
		setActionCommands();
	}

	protected void initLabels() {
		selectId = new JLabel("Select an ID");
		specifyTopicFilterExpression = new JLabel("Specify a Topic Filter Expression");
		specifyParametricPredicate = new JLabel("Specify a Parametric Predicate");
		selectOperation = new JLabel("Select a Permission");

		userId = new JLabel("Users:");
		clientId = new JLabel("Clients:");
		parametricPredicate = new JLabel("Parametic Predicate:");
		setFonts();
	}

	protected void initComboBoxes() {
		usersIds = new JComboBox<>();
		clientsIds = new JComboBox<>();
		permissions = new JComboBox<>(AdminGuiComponentsConstants.PERMISSIONS);
	}

	protected void initTextFields() {
		topicFilterExpressionInput = new JTextField(40);
	}

	protected void initPanels() {
		initPanel1();
		initPanel2();
		initPanel3();
		initPanel4();
	}

	protected void initPanel1() {
		panel1 = new JPanel(new MigLayout("fill", "[][][][]", "[][][]"));
		panel1.add(selectId, "cell 1 0 2 1,alignx center");
		panel1.add(userId, "cell 0 1,alignx right");
		panel1.add(usersIds, "cell 1 1");
		panel1.add(clientId, "cell 2 1,alignx right");
		panel1.add(clientsIds, "cell 3 1");
		panel1.add(cancel, "cell 0 2,aligny bottom");
		panel1.add(next, "cell 3 2,alignx right,aligny bottom");
	}

	protected void initPanel2() {
		panel2 = new JPanel(new MigLayout("fill", "[][]", "[][][]"));
		panel2.add(specifyTopicFilterExpression, "cell 0 0 2 1,alignx center,aligny center");
		panel2.add(topicFilterExpressionInput, "cell 0 1 2 1,alignx left");
		panel2.add(back, "cell 0 2,aligny bottom");
		panel2.add(next1, "cell 1 2,alignx right,aligny bottom");
	}

	protected void initPanel3() {
		panel3 = new JPanel(new MigLayout("fill", "[][]", "[][][]"));
		panel3.add(specifyParametricPredicate, "cell 0 0 2 1,alignx center,aligny top");
		panel3.add(editor.getMainPanel(), "cell 0 1,span 2,grow");
		panel3.add(back1, "cell 0 2,aligny bottom");
		panel3.add(next2, "cell 1 2,alignx right,aligny bottom");
	}

	protected void initPanel4() {
		panel4 = new JPanel(new MigLayout("fill", "[][][]", "[][][]"));
		panel4.add(selectOperation, "cell 1 0,alignx center");
		panel4.add(permissions, "cell 1 1,alignx center");
		panel4.add(back2, "cell 0 2,aligny bottom");
		panel4.add(finish, "cell 2 2,alignx right,aligny bottom");

	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new CardLayout());
		mainPanel.add(panel1, ID_SELECTION_PANEL);
		mainPanel.add(panel2, TF_SPECIFICATION_PANEL);
		mainPanel.add(panel3, EXP_SPECIFICATION_PANEL);
		mainPanel.add(panel4, PR_SELECTION_PANEL);
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Add an Access Control Policy");
		mainDialog.setBounds(450, 160, 350, 380);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.setResizable(false);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected void setFonts() {
		selectId.setFont(new Font("Tahoma", Font.BOLD, 15));
		specifyTopicFilterExpression.setFont(new Font("Tahoma", Font.BOLD, 15));
		specifyParametricPredicate.setFont(new Font("Tahoma", Font.BOLD, 15));
		parametricPredicate.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectOperation.setFont(new Font("Tahoma", Font.BOLD, 15));
		userId.setFont(new Font("Tahoma", Font.BOLD, 12));
		clientId.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	protected void setActionCommands() {
		cancel.setActionCommand("CANCEL");
		next.setActionCommand("NEXT");
		next1.setActionCommand("NEXT");
		next2.setActionCommand("NEXT");
		back.setActionCommand("BACK");
		back1.setActionCommand("BACK");
		back2.setActionCommand("BACK");
		finish.setActionCommand("FINISH");
	}

	public void addAddPolicyDialogButtonsListener(ActionListener e) {
		cancel.addActionListener(e);
		next.addActionListener(e);
		next1.addActionListener(e);
		next2.addActionListener(e);
		back.addActionListener(e);
		back1.addActionListener(e);
		back2.addActionListener(e);
		finish.addActionListener(e);
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
		return acPolicy;

	}

	public void show() {
		mainDialog.setVisible(true);
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

	public ParametricExpressionEditor getParametricExpressionEditor() {
		return editor;
	}

	public JButton getFinish() {
		return finish;
	}

	public JButton getCancel() {
		return cancel;
	}
}