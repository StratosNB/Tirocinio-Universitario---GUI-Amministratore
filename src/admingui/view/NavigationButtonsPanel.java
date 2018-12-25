package admingui.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class NavigationButtonsPanel {

	private JPanel mainPanel;

	private JButton add;
	private JButton delete;

	private JButton first;
	private JButton prev;
	private JButton next;
	private JButton last;

	public NavigationButtonsPanel() {
		initComponents();
	}

	public void initComponents() {

		initButtons();
		initMainPanel();
	}

	protected void initButtons() {
		add = new JButton("Add...");
		delete = new JButton("Delete");

		first = new JButton("First");
		prev = new JButton("Previous");
		next = new JButton("Next");
		last = new JButton("Last");
	}

	protected JPanel initMainPanel() {
		mainPanel = new JPanel(new MigLayout("center", "[]30[][][][]30[]"));
		// mainPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		mainPanel.add(delete, "cell 0 0");
		mainPanel.add(first, "cell 1 0");
		mainPanel.add(prev, "cell 2 0");
		mainPanel.add(next, "cell 3 0");
		mainPanel.add(last, "cell 4 0");
		mainPanel.add(add, "cell 5 0");
		mainPanel.setVisible(true);
		return mainPanel;
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public JButton getDeleteButton() {
		return this.delete;
	}

	public JButton getFirstButton() {
		return this.first;
	}

	public JButton getPreviosuButton() {
		return this.prev;
	}

	public JButton getNextButton() {
		return this.next;
	}

	public JButton getLastButton() {
		return this.last;
	}

	public JButton getAddButton() {
		return this.add;
	}
}
