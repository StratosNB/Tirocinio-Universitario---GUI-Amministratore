package view.userPanes;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import models.UserAttribute;
import net.miginfocom.swing.MigLayout;
import view.attributePanes.AttributeSetView;

@SuppressWarnings("serial")
public class ClientAttributeSetManagement extends JPanel {

	private AttributeSetView attributeSetView;

	private JPanel mainPanel;

	private final String[] types = { "String", "Int", "Date" };

	private JComboBox boxTypes = new JComboBox(types);

	private JTextField newAttributeSetNameTxt = new JTextField(15);

	private JButton addBtn = new JButton("Add");

	private JButton backBtn = new JButton("Back");
	private JButton nextBtn = new JButton("Next");;

	public ClientAttributeSetManagement() {

		setLayout(new CardLayout());
		initComponents();
	}

	protected void initComponents() {

		attributeSetView = new AttributeSetView();

		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		mainPanel = new JPanel(layout);

		// put all the panels together
		mainPanel.add(attributeSetView, "flowx,alignx left,growy");
		mainPanel.add(getAddAttributeSetPanel(), "cell 0 1");
		mainPanel.add(getNavButtonsPanel(), "south");

		add(mainPanel, "main attributeSet panel");

	}

	private JPanel getAddAttributeSetPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new MigLayout("wrap 3", "[] 16 []"));

		panel.add(new JLabel("Name:"), "right");
		panel.add(newAttributeSetNameTxt);

		panel.add(addBtn);

		return panel;

	}

	private JPanel getNavButtonsPanel() {

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("center", "[] []"));

		panel.add(backBtn);
		panel.add(nextBtn);

		return panel;
	}

	public void addNavListener(ActionListener navListener) {

		backBtn.addActionListener(navListener);
		nextBtn.addActionListener(navListener);

	}

	public void addCRUDListener(ActionListener crudListener) {

		addBtn.addActionListener(crudListener);

	}

	public UserAttribute getNewAttributeName() {
		UserAttribute attribute = new UserAttribute();
		attribute.setAttributeName(newAttributeSetNameTxt.getText());
		
		System.out.println("method get new attribute name : " + newAttributeSetNameTxt.getText());

		return attribute;
	}

	public boolean isEmpty() {
		return (newAttributeSetNameTxt.getText().trim().isEmpty());
	}

	public JTextField getNewAttributeNameTextField() {
		return newAttributeSetNameTxt;
	}

	public AttributeSetView getAttribSetView() {
		return this.attributeSetView;
	}

}
