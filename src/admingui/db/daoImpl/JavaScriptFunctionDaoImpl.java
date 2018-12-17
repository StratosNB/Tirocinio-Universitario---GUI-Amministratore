package admingui.db.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import admingui.db.ConnectToDatabase;
import admingui.db.dao.JavaScriptFunctionDao;
import admingui.models.JavaScriptFunction;

public class JavaScriptFunctionDaoImpl implements JavaScriptFunctionDao {

	private final Connection conn = ConnectToDatabase.createConnection();
	private final String SQL_CREATE_JS_FUNCTION = "INSERT INTO js_functions (name,code) VALUES (?,?)";
	private final String SQL_GET_ALL_JS_FUNCTIONS = "SELECT * FROM js_functions ORDER BY id";
	private final String SQL_GET_JS_FUNCTION_BY_NAME = "SELECT * FROM js_functions WHERE name=? ORDER BY id";
	private final String SQL_UPDATE_JS_FUNCTION_BY_NAME = "UPDATE js_functions SET name=?, code=? WHERE name=?";
	private final String SQL_DELETE_JS_FUNCTION_BY_NAME = "DELETE FROM js_functions WHERE name=?";

	@Override
	public void create(JavaScriptFunction function) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_JS_FUNCTION, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, function.getName());
			pstmt.setString(2, function.getCode());
			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					function.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<JavaScriptFunction> getAll() {
		List<JavaScriptFunction> functions = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_JS_FUNCTIONS);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				JavaScriptFunction function = new JavaScriptFunction();
				function.setId(rs.getInt(1));
				function.setName(rs.getString(2).trim());
				function.setCode(rs.getString(3));
				functions.add(function);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return functions;
	}

	@Override
	public JavaScriptFunction getFunctionByName(String name) {
		JavaScriptFunction function = new JavaScriptFunction();
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_JS_FUNCTION_BY_NAME)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				function.setId(rs.getInt(1));
				function.setName(rs.getString(2).trim());
				function.setCode(rs.getString(3));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return function;
	}

	@Override
	public void updateByName(JavaScriptFunction function, String oldName) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_JS_FUNCTION_BY_NAME)) {
			pstmt.setString(1, function.getName());
			pstmt.setString(2, function.getCode());
			pstmt.setString(3, oldName);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteByName(String name) {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_JS_FUNCTION_BY_NAME)) {
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}