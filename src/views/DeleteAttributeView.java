package views;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class DeleteAttributeView extends JPanel {

	private String who;
	private String title;
	private JButton deleteButton = new JButton("Delete");

	private String[] something = { "something", "something", "something" };

	private JComboBox usersAttributes = new JComboBox(something);
	private JComboBox clientsAttributes = new JComboBox(something);

	public DeleteAttributeView(String s) {

		this.who = s;
		this.title = who + " attributes";
		initComponents();
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(getDeleteAttributePanel());
	}

	private JPanel getDeleteAttributePanel() {
		JPanel panel = getPanel(title);
		panel.setLayout(new MigLayout("wrap 2", "[] 16 []"));

		panel.add(new JLabel("Name:"), "right");
		if (who.equals("Users")) {
			panel.add(usersAttributes);
		}
		if (who.equals("Clients")) {
			panel.add(clientsAttributes);
		}
		panel.add(deleteButton, "cell 2 2");

		return panel;

	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
}
