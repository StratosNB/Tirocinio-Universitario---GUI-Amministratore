package admingui.utils;

import sun.util.calendar.BaseCalendar.Date;

public class AdminGuiStrings {

	public final static String[] attributeValuesTypes = { String.class.getSimpleName(), Integer.class.getSimpleName(),
			Double.class.getSimpleName(), Date.class.getSimpleName(), Boolean.class.getSimpleName() };

	public final static String[] fixedAttributesTableColumns = { "Name", "Type" };
	public final static String[][] userFixedAttributesTableData = { { "ID", "Int" }, { "Name", "String" },
			{ "Surname", "String" }, { "Clients owned", "Int" } };
	public final static String[][] clientFixedAttributesTableData = { { "ID", "Int" }, { "Name", "String" },
			{ "Owner ID", "Int" } };

	public final static String[] roles = { "Admin", "User" };
	public final static String[] searchRoles = { "", "Admin", "User" };

	public final static String[] clientTableColumns = { "ID", "Name" };

	public final static String[] entities = { "User", "Client" };

}
