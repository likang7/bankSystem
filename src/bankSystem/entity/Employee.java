package bankSystem.entity;



public class Employee {
	private String username;
	private String password;
	private Position position; 
	private String departmentId;
	
	public Employee(String username, String password, Position position,
			String departmentId) {
		super();
		this.username = username;
		this.password = password;
		this.position = position;
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
