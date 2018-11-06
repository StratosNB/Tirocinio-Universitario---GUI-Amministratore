package view.attributePanes;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class AttributeSetView extends JPanel {

	private JPanel attribSetPanel;
	private JScrollPane scrollablePanel;

	public AttributeSetView() {

		initComponents();
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 2", "[grow] [] ", "[] [] []");
		setLayout(layout);

		// put all the panels together
		add(getAttributeSetPanel(), "span 3");

	}

	private void setAttributeSetPanel() {
		attribSetPanel = new JPanel();
		attribSetPanel.setLayout(new MigLayout("wrap 4", "[] 16 []"));
	}

	private JScrollPane getAttributeSetPanel() {

		setAttributeSetPanel();
		scrollablePanel = new JScrollPane(attribSetPanel);
		scrollablePanel
				.setBorder(new TitledBorder(null, "Attribute Sets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollablePanel.setPreferredSize(new Dimension(330, 280));

		return scrollablePanel;

	}

	public JPanel getAttribPanel() {
		return this.attribSetPanel;
	}

	public JScrollPane getAttributesScrollPane() {
		return this.scrollablePanel;
	}

}
