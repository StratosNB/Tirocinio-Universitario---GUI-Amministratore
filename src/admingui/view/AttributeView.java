package admingui.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import admingui.model.Attribute;
import net.miginfocom.swing.MigLayout;

public class AttributeView {

	private Attribute attribute;

	private JPanel mainPanel;

	private JLabel name;
	private JLabel value;

	public AttributeView(Attribute attribute) {
		this.attribute = attribute;
		initComponents();
	}

	protected void initComponents() {
		initLabels();
		initMainPanel();
	}

	protected void initLabels() {
		name = new JLabel(attribute.getName() + ":");
		name.setFont(new Font("Tahoma", Font.BOLD, 11));
		value = new JLabel(attribute.getValue());
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]"));
		mainPanel.add(name);
		mainPanel.add(value);
		mainPanel.setVisible(true);
	}

	public void setValue(String value) {
		this.value.setText(value);
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
