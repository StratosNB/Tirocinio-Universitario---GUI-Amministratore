package admingui.views.attributes.dialogs_panels;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import admingui.models.Attribute;

import net.miginfocom.swing.MigLayout;

public class AdditionalAttributesPanel {

	private JPanel mainPanel;
	private JScrollPane scrollPanel;

	private List<Attribute> attributes;
	private List<AttributeView> views;

	public AdditionalAttributesPanel() {
		initComponents();
	}

	protected void initComponents() {
		mainPanel = new JPanel(new MigLayout("wrap 3", "[]30[]30[]"));
		views = new ArrayList<>();
		attributes = new ArrayList<>();
		initScrollPane();
	}

	protected void initScrollPane() {
		scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setPreferredSize(new Dimension(500, 350));
		scrollPanel.setBorder(BorderFactory.createTitledBorder("Additional Attributes"));
	}

	public void createAttributeViews(List<Attribute> attributes) {
		if (attributes.size() != -1) {
			views.clear();
			this.attributes.clear();
			this.attributes.addAll(attributes);
			Iterator<Attribute> iter = this.attributes.iterator();
			while (iter.hasNext()) {
				Attribute attribute = iter.next();
				AttributeView view = new AttributeView(attribute);
				views.add(view);
				mainPanel.add(view.getMainPanel());
			}
		}
	}

	public void setValuesInAttributesViews(List<Attribute> attributes) {
		if (this.attributes.size() != -1) {
			int i = 0;
			for (AttributeView view : this.views) {
				view.setValue(attributes.get(i++).getValue());
			}
		}
	}

	public void updateAttributesList(List<Attribute> attributes) {
		attributes.clear();
		attributes.addAll(attributes);
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public JScrollPane getScrollPane() {
		return scrollPanel;
	}

	public void removeBorder() {
		scrollPanel.setBorder(null);
	}
}
