package admingui.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import admingui.model.Attribute;
import admingui.model.User;
import admingui.utils.AdminGuiConstants;
import net.miginfocom.swing.MigLayout;

public class UserForm {

	private final String title;
	private JPanel mainPanel;
	private JPanel additionalAttributesForm;
	private JLabel id;
	private JLabel name;
	private JLabel surname;
	private JLabel role;
	private JTextField userId;
	private JTextField userName;
	private JTextField userSurname;
	private JComboBox<String> userRoles;

	private List<Attribute> attributes;

	public UserForm(String title) {
		this.title = title;
		attributes = new ArrayList<>();
		initComponents();
	}

	protected void initComponents() {
		initLabels();
		initFields();
		initMainPanel();
	}

	protected void initLabels() {
		id = new JLabel("ID:");
		name = new JLabel("Name:");
		surname = new JLabel("Surname:");
		role = new JLabel("Role:");
	}

	protected void initFields() {
		userId = new JTextField(5);
		userName = new JTextField(20);
		userSurname = new JTextField(20);
		userRoles = new JComboBox<String>(AdminGuiConstants.SEARCH_PANEL_ROLES);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[]", "[][]"));
		mainPanel.setBorder(BorderFactory.createTitledBorder(title));
		mainPanel.setVisible(true);
		mainPanel.add(createFixedAttributesForm());
		mainPanel.add(createAttributesScrollPane(), "grow");
	}

	protected JPanel createFixedAttributesForm() {
		JPanel panel = new JPanel(new MigLayout("wrap 2"));
		panel.add(id, "right");
		panel.add(userId);
		panel.add(name, "right");
		panel.add(userName);
		panel.add(surname, "right");
		panel.add(userSurname);
		panel.add(role, "right");
		panel.add(userRoles);
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

	public void setFixedAttributesData(User user) {
		userId.setText(String.valueOf(user.getId()));
		userName.setText(String.valueOf(user.getName()));
		userSurname.setText(String.valueOf(user.getSurname()));
		userRoles.setSelectedItem(String.valueOf(user.getRole()));
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

	public User createUser() {
		User user = new User();
		if (userId.getText().isEmpty()) {
			user.setId(0);
		} else {
			user.setId(Integer.valueOf(userId.getText().trim()));
		}
		user.setName(userName.getText().trim());
		user.setSurname(userSurname.getText().trim());
		user.setRole(userRoles.getSelectedItem().toString());
		user.setAdmin(userRoles.getSelectedItem().toString().trim());
		return user;
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

	public List<Attribute> getAllNotNullInputAttributes() {
		Component[] components = additionalAttributesForm.getComponents();
		List<String> attributesNames = new ArrayList<>();
		List<String> attributesValues = new ArrayList<>();
		List<Attribute> attributes = new ArrayList<>();

		for (Component c : components) {
			if (c instanceof JLabel) {
				String name = ((JLabel) c).getText();
				name = name.replace(":", "");
				attributesNames.add(name);
			}
			if (c instanceof JTextField) {
				attributesValues.add(((JTextField) c).getText());
			}
		}

		for (int i = 0; i < attributesNames.size(); i++) {
			if (!attributesValues.get(i).isEmpty()) {
				Attribute attribute = new Attribute();
				attribute.setName(attributesNames.get(i));
				attribute.setValue(attributesValues.get(i));
				attributes.add(attribute);
			}
		}
		return attributes;
	}

	public void clearTexts() {
		userId.setText("");
		userName.setText("");
		userSurname.setText("");
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
		return userId.getText().isEmpty() || userName.getText().isEmpty() || userSurname.getText().isEmpty()
				|| userRoles.getSelectedItem().toString().isEmpty();
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public JComboBox<String> getUserRoles() {
		return userRoles;
	}
}