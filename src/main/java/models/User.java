package models;

import java.io.Serializable;

public class User implements Serializable {
 
	private static final long serialVersionUID=1L;
	
	private Long id;
	private int code;
	private String pasword;
	private boolean administrator;
	private String name;
	
	/**
	 * Constructor con todos los parametros, para usar cuando se obtenga de la base de datos
	 * @param id
	 * @param code
	 * @param pasword
	 * @param administrator
	 * @param name
	 */
	public User(Long id, int code, String pasword, boolean administrator, String name) {
		super();
		this.id = id;
		this.code = code;
		this.pasword = pasword;
		this.administrator = administrator;
		this.name = name;
	}
	/**
	 * Constructor para crear un usuario que no este en la base de datos
	 * @param code
	 * @param administrator
	 * @param name
	 */
	public User(int code, boolean administrator, String name) {
		this.id=-1L;
		//generar contraseña
		this.code = code;
		this.administrator = administrator;
		this.name = name;
	}
	
	
	public User() {
		this(-1L,-1,"",false,"");
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (code != other.code)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", code=" + code + ", pasword=" + pasword + ", administrator=" + administrator
				+ ", name=" + name + "]";
	}
	
	
	
	
	
	
}
