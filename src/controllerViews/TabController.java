/*package controllerViews;

import javax.swing.JTabbedPane;

import controller.Beans.AttributeBean;
import controller.Beans.ClientBean;
import controller.Beans.UserBean;

public class TabController {

	UserBean userBean = new UserBean();
	ClientBean clientBean = new ClientBean();
	AttributeBean attribBeanUser = new AttributeBean("user_attributes");
	AttributeBean attribBeanClient = new AttributeBean("client_attributes");

	JTabbedPane tabbedP;

	public TabController(JTabbedPane tabbedPane) {

		this.tabbedP = tabbedPane;

		if (userBean.getRowSet() == null || attribBeanUser.getRowSet() == null) {

			this.tabbedP.setEnabledAt(0, true);
			this.tabbedP.setSelectedIndex(3);
		}

		if (clientBean.getRowSet() == null || attribBeanClient.getRowSet() == null) {

			this.tabbedP.setEnabledAt(1, true);
			this.tabbedP.setSelectedIndex(3);
		}

	}

}
*/