package project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "code", length = 3)
	private int code;
	@Column(name = "password", length = 10)
	private String password;
	@Column(name = "administrator")
	private boolean administrator;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "name", length = 100)
	private String name;

	/**
	 * Constructor con todos los parametros, para usar cuando se obtenga de la base
	 * de datos
	 * 
	 * @param id
	 * @param code
	 * @param pasword
	 * @param administrator
	 * @param name
	 */
	public User(Long id, int code, String pasword, boolean administrator, String email, String name) {
		super();
		this.id = id;
		this.code = code;
		this.password = pasword;
		this.administrator = administrator;
		this.email = email;
		this.name = name;
	}

	/**
	 * Constructor para crear un usuario que no este en la base de datos
	 * 
	 * @param code
	 * @param administrator
	 * @param name
	 */
	public User(int code, boolean administrator, String email, String name) {
		super();
		this.code = code;
		this.administrator = administrator;
		this.email = email;
		this.name = name;
	}

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "User [id=" + id + ", code=" + code + ", pasword=" + password + ", administrator=" + administrator
				+ ", name=" + name + "]";
	}

}
