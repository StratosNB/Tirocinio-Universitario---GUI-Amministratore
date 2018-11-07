package view.attributePanes;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.UserAttribute;
import net.miginfocom.swing.MigLayout;

public class AttributesView extends JPanel {

	private JPanel attribPanel;
	private JScrollPane scrollablePanel;

	public AttributesView() {

		attribPanel = new JPanel();
		attribPanel.setLayout(new MigLayout("wrap 2", "[] 16 []"));
		initComponents();
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(getAttributesPanel(), "span 3");

	}

	private JScrollPane getAttributesPanel() {

		scrollablePanel = new JScrollPane(attribPanel);
		scrollablePanel.setBorder(
				new TitledBorder(null, "Additional attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollablePanel.setPreferredSize(new Dimension(330, 150));

		return scrollablePanel;

	}

	public JPanel getAttribPanel() {
		return this.attribPanel;
	}

	public JScrollPane getAttributesScrollPane() {
		return this.scrollablePanel;
	}

	public ArrayList<UserAttribute> getFieldData(String who, String id) {

		ArrayList<UserAttribute> attributes = new ArrayList<UserAttribute>();
		UserAttribute attribute = new UserAttribute();
/*
		for (int i = 0; i < getAttribPanel().getComponentCount() / 2; i++) {

			Component[] c = getAttribPanel().getComponents();

			if (who.equals("user")) {
				attribute.setUserID(id);
			} else {
				attribute.setClientID(id);
			}

			if (c[i] instanceof JLabel) {
				attribute.setAttributeName(((JLabel) c[i]).getText());
			}

			if (c[i] instanceof JTextField) {
				attribute.setAttributeValue(((JTextField) c[i]).getText());
			}

			attributes.add(attribute);

		}*/
		return attributes;
	}

	public void setFieldData(ArrayList<UserAttribute> attributes) {
		// TODO Auto-generated method stub

	}

	public boolean isEmptyFieldData() {

		for (Component c : getAttribPanel().getComponents()) {

			if (c instanceof JTextField && ((JTextField) c).getText().trim().isEmpty()) {

				return true;

			}

		}

		return false;
	}
}
