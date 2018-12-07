package admingui.db.contract;

public class DbContract {
	public static final String HOST = "jdbc:postgresql://localhost:5432/";
	public static final String DB_NAME = "MQTTForIOT_DB";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "123";
	public static final String DB_URL = HOST + DB_NAME + "/" + USERNAME + "/" + PASSWORD;

	public static final String USERS_ATTRIBUTES_TABLE_NAME = "users_attributes";
	public static final String CLIENTS_ATTRIBUTES_TABLE_NAME = "clients_attributes";
}
