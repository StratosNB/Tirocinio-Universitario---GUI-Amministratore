package admingui.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import admingui.model.Attribute;
import admingui.model.Client;
import net.miginfocom.swing.MigLayout;

public class ClientForm {

	private final String title;

	private JPanel mainPanel;
	private JPanel additionalAttributesForm;

	private JLabel id;
	private JLabel name;

	private JTextField clientId;
	private JTextField clientName;

	private List<Attribute> attributes;

	public ClientForm(String title) {
		this.title = title;
		attributes = new ArrayList<>();
		initComponents();
	}

	protected void initComponents() {
		initLabels();
		initTexts();
		initMainPanel();
	}

	protected void initLabels() {
		id = new JLabel("Id:");
		name = new JLabel("Name:");
	}

	protected void initTexts() {
		clientId = new JTextField(5);
		clientName = new JTextField(20);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.setBorder(BorderFactory.createTitledBorder(title));
		mainPanel.setVisible(true);
		mainPanel.add(createFixedAttributesForm());
		mainPanel.add(createAttributesScrollPane());
	}

	protected JPanel createFixedAttributesForm() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.add(id, "right");
		panel.add(clientId);
		panel.add(name, "right");
		panel.add(clientName);
		return panel;
	}

	protected JPanel createAdditionalAttributesForm() {
		additionalAttributesForm = new JPanel(new MigLayout("Wrap 2"));
		additionalAttributesForm.setBorder(null);
		return additionalAttributesForm;
	}

	protected JScrollPane createAttributesScrollPane() {
		JScrollPane scrollPane = new JScrollPane(createAdditionalAttributesForm());
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(300, 400));
		return scrollPane;
	}

	public void setFixedAttributesData(Client client) {
		clientId.setText(String.valueOf(client.getId()));
		clientName.setText(client.getName());
	}

	public void createAdditionalAttributesComponents(List<Attribute> attributes) {
		this.attributes.clear();
		this.attributes.addAll(attributes);
		JTextField value = new JTextField(20);
		additionalAttributesForm.removeAll();
		for (Attribute a : attributes) {
			value = new JTextField(20);
			value.setText(a.getValue());
			additionalAttributesForm.add(new JLabel(a.getName() + ":"), "right");
			additionalAttributesForm.add(value);
		}
	}

	public void createEmptyAdditionalAttributesComponents(List<Attribute> attributes) {
		JTextField value = new JTextField(20);
		additionalAttributesForm.removeAll();
		for (Attribute a : attributes) {
			value = new JTextField(20);
			additionalAttributesForm.add(new JLabel(a.getName() + ":"), "right");
			additionalAttributesForm.add(value);
		}
	}

	public void updateAttributesListValues() {
		Component[] components = additionalAttributesForm.getComponents();
		int i = 0;
		for (Component c : components) {
			if (c instanceof JTextField) {
				attributes.get(i++).setValue(((JTextField) c).getText());
			}
		}
	}

	public Client createClient() {
		Client client = new Client();
		client.setId(Integer.valueOf(clientId.getText().trim()));
		client.setName(clientName.getText().trim());
		return client;
	}

	public List<Attribute> createAttributes(List<Attribute> noValueAttributes) {
		Component[] components = additionalAttributesForm.getComponents();
		int i = 0;
		for (Component c : components) {
			if (c instanceof JTextField) {
				noValueAttributes.get(i++).setValue(((JTextField) c).getText());
			}
		}
		return noValueAttributes;
	}

	public void clearTexts() {
		clientId.setText("");
		clientName.setText("");
		clearAdditionalAttributesTexts();
	}

	public void clearAdditionalAttributesTexts() {
		Component[] components = additionalAttributesForm.getComponents();
		for (Component c : components) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}

	public boolean isEmptyData() {
		return clientId.getText().isEmpty() || clientName.getText().isEmpty();
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}
}
