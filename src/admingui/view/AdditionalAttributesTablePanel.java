package admingui.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import admingui.model.Attribute;
import admingui.model.AttributeTableModel;
import admingui.view.dialogs.AddAttributeDialog;
import admingui.view.dialogs.EditAttributeDialog;
import net.miginfocom.swing.MigLayout;

public class AdditionalAttributesTablePanel {

	private JPanel mainPanel;

	private String tableName;

	private JButton add;
	private JButton edit;
	private JButton delete;

	private JTable attributeTable;
	private AttributeTableModel tableModel;

	private AddAttributeDialog addAttribute;
	private EditAttributeDialog editAttribute;

	private JScrollPane scrollPane;

	private DefaultTableCellRenderer leftRenderer;

	public AdditionalAttributesTablePanel(String tableName) {
		this.tableName = tableName;
		initComponents();
	}

	protected void initComponents() {
		initButtons();
		tableModel = new AttributeTableModel(tableName);
		initTable();
		scrollPane = new JScrollPane(attributeTable);
		initDialogs();
		initRenderer();
		initMainPanel();
	}

	protected void initButtons() {
		add = new JButton("Add...");
		edit = new JButton("Edit...");
		delete = new JButton("Delete");
	}

	protected void initDialogs() {
		addAttribute = new AddAttributeDialog();
		editAttribute = new EditAttributeDialog(new Attribute());
	}

	protected void initTable() {
		attributeTable = new JTable(tableModel);
		attributeTable.setFillsViewportHeight(true);
	}

	protected void initRenderer() {
		leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		attributeTable.getColumn("Name").setCellRenderer(leftRenderer);
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[]", "[][]0"));
		mainPanel.setVisible(true);
		mainPanel.add(scrollPane, "cell 0 0");
		mainPanel.add(createButtonsPanel(), "cell 0 1");
	}

	protected JPanel createButtonsPanel() {
		JPanel controlTablePanel = new JPanel(new MigLayout("", "[][][]", "[]"));
		controlTablePanel.add(add);
		controlTablePanel.add(edit);
		controlTablePanel.add(delete);
		return controlTablePanel;
	}

	public Attribute getSelectedAttribute() {
		Attribute attribute = new Attribute();
		int selectedRow = attributeTable.getSelectedRow();
		attribute.setName((String) tableModel.getValueAt(selectedRow, 0));
		attribute.setType((String) tableModel.getValueAt(selectedRow, 0));
		return attribute;
	}

	public void addRowChangedListener(TableModelListener e) {
		attributeTable.getModel().addTableModelListener(e);
	}

	public void addRowClickedListener(MouseListener m) {
		attributeTable.addMouseListener(m);
	}

	public void addCrudListeners(ActionListener e) {
		add.addActionListener(e);
		edit.addActionListener(e);
		delete.addActionListener(e);
	}

	public void addCancelListener(ActionListener e) {
		addAttribute.getCancel().addActionListener(e);
		editAttribute.getCancel().addActionListener(e);
	}

	public void addChangesListener(TableModelListener e) {
		tableModel.addTableModelListener(e);
	}

	public AddAttributeDialog getAddAttribute() {
		return addAttribute;
	}

	public EditAttributeDialog getEditAttribute() {
		return editAttribute;
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}

	public JButton getCreateAttribute() {
		return add;
	}

	public JButton getUpdateAttribute() {
		return edit;
	}

	public JButton getDeleteAttribute() {
		return delete;
	}

	public JTable getAttributeTable() {
		return attributeTable;
	}

	public AttributeTableModel getTableModel() {
		return tableModel;
	}

	public String getTableName() {
		return tableName;
	}
}