package bankSystem.entity;

import java.io.Serializable;



public class Employee implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Position position; 
	private String departmentId;
	private String superiorId;
	
	public Employee(String username, String password, Position position,
			String departmentId, String superiorId) {
		super();
		this.username = username;
		this.password = password;
		this.position = position;
		this.departmentId = departmentId;
		this.superiorId = superiorId;
	}

	public Employee(String username, String password, String strPosition,
			String departmentId, String superiorId) {
		// TODO Auto-generated constructor stub
		super();
		Position p = Position.Operator;
		try{
			if(strPosition.equalsIgnoreCase(Position.Administrator.toString())){
				p = Position.Administrator;
			}
			else if(strPosition.equalsIgnoreCase(Position.Supervisor.toString())){
				p = Position.Supervisor;
			}
			else if(strPosition.equalsIgnoreCase(Position.Manager.toString())){
				p = Position.Manager;
			}
			else if(strPosition.equalsIgnoreCase(Position.Operator.toString())){
				p = Position.Operator;
			}
			else {
				throw new Exception();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		this.username = username;
		this.password = password;
		this.position = p;
		this.departmentId = departmentId;
		this.superiorId = superiorId;
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

	public String getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}

	@Override
	public String toString() {
		return "username=" + username + ", position=" + position
				+ ", departmentId=" + departmentId + ", superiorId="
				+ superiorId	;
	}
}
