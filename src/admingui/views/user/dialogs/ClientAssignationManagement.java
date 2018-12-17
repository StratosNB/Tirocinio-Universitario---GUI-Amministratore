package admingui.views.user.dialogs;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class ClientAssignationManagement {
	
	private JDialog mainDialog;
	private JPanel mainPanel;
	
	private JSplitPane splitPane1;
	private JSplitPane splitPane2;
	
	private JTable assignedClientsTable;
	private JTable avaibleClientsTable;
	
	private JButton addAvaibleClientButton;
	private JButton removeAssignedClientButton;
	private JButton closeButton;
	
	public ClientAssignationManagement(){
		initComponents();
	}
	
	protected void initComponents(){
		initButtons();
	}
	
	protected void initButtons(){
		
	}

}
