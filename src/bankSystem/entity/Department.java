package bankSystem.entity;

import java.io.Serializable;

public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String superiorId;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String id, String name, String superiorId) {
		super();
		this.id = id;
		this.name = name;
		this.superiorId = superiorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}
		
}
