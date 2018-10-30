package viewPanels;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Attribute;
import net.miginfocom.swing.MigLayout;
import views.DeleteAttributeView;

public class PaneAttributes extends JPanel {

	private final String[] types = { "String", "Int", "Date" };
	private final String[] entities = { "Users", "Clients" };

	private JTextField txtName = new JTextField(10);
	private JComboBox boxTypes = new JComboBox(types);
	private JComboBox boxFor = new JComboBox(entities);

	private JButton addButton = new JButton("Add");

	private JPanel AddAttributePane;

	public PaneAttributes() {

		initComponents();

	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 2", "[] 16 []");
		setLayout(layout);

		// put all the panels together
		add(getAddAttributePanel(), "cell 0 0");
		add(new DeleteAttributeView("Users"), "cell 0 1");
		add(new DeleteAttributeView("Clients"), "cell 0 2");

	}

	private JPanel getAddAttributePanel() {

		AddAttributePane = getPanel("New Attribute");
		AddAttributePane.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		AddAttributePane.add(new JLabel("Name:"), "right");
		AddAttributePane.add(txtName);

		AddAttributePane.add(new JLabel("Type:"), "right");
		AddAttributePane.add(boxTypes);

		AddAttributePane.add(new JLabel("For:"), "right");
		AddAttributePane.add(boxFor);

		AddAttributePane.add(addButton, "cell 2 2");

		return AddAttributePane;

	}

	public Attribute getFieldData() {
		Attribute attribute = new Attribute();
		attribute.setAttributeName(txtName.getText().trim());
		attribute.setAttributeType((String) boxTypes.getSelectedItem());

		return attribute;
	}

	public JTextField getNameTxt() {
		return txtName;
	}

	public String getSelectedEntity() {
		return (String) boxFor.getSelectedItem();
	}

	public boolean isEmptyFieldData() {
		return (txtName.getText().trim().isEmpty() || boxTypes.getSelectedIndex() == -1
				|| boxFor.getSelectedIndex() == -1);
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	public void addAddListener(ActionListener addListener) {

		addButton.addActionListener(addListener);
	}

}
