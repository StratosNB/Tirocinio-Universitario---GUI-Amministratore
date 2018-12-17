package admingui.models;

public class JavaScriptFunction {

	int id;
	String name;
	String code;

	public JavaScriptFunction() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "JavaScriptFunction [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}