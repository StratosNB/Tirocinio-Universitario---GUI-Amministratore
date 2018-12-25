package admingui.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import admingui.model.Attribute;
import net.miginfocom.swing.MigLayout;

public class AdditionalAttributesViewPanel {

	private JScrollPane sp;
	private JPanel mainPanel;

	public AdditionalAttributesViewPanel() {
		initComponents();
	}

	protected void initComponents() {
		mainPanel = new JPanel(new MigLayout("wrap 2"));
		sp = new JScrollPane(mainPanel);
		sp.setPreferredSize(new Dimension(300,200));
	}

	public void setAttributesViews(List<Attribute> attributes) {
		mainPanel.removeAll();
		attributes.forEach(attribute -> mainPanel.add(new AttributeView(attribute).getMainPanel()));
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public JScrollPane getScrollPane() {
		return sp;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}