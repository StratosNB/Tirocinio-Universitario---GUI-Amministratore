package admingui.utils;

import sun.util.calendar.BaseCalendar.Date;

public class AdminGuiStrings {

	public final static String[] ATTRIBUTE_VALUES_TYPES = { String.class.getSimpleName(), Integer.class.getSimpleName(),
			Double.class.getSimpleName(), Date.class.getSimpleName(), Boolean.class.getSimpleName() };

	public final static String[] FIXED_ATTRIBUTES_TABLE_COLUMNS = { "Name", "Type" };
	public final static String[][] USERS_FIXED_ATTRIBUTES_TABLE_DATA = { { "ID", "Int" }, { "Name", "String" },
			{ "Surname", "String" }, { "Clients owned", "Int" } };
	public final static String[][] CLIENTS_FIXED_ATTRIBUTES_TABLE_DATA = { { "ID", "Int" }, { "Name", "String" },
			{ "Owner ID", "Int" } };
	
	public final static String[][] OBJECTS_FIXED_ATTRIBUTES_TABLE_DATA = {{"Topic","String"}};

	public final static String[] ROLES = { "Admin", "User" };
	public final static String[] SEARCH_PANEL_ROLES = { "", "Admin", "User" };

	public final static String[] CLIENT_TABLE_COLUMNS = { "ID", "Name" };

	public final static String[] ENTITIES = { "", "Users", "Clients" };

	public final static String[] PERMISSIONS = { "", "Read", "Write" };
}