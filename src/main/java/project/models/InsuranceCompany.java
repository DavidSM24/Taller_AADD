package project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@javax.persistence.Embeddable
@javax.persistence.Table(name="insurancecompany")
public class InsuranceCompany implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	@Column(name="CIA_Name")
	protected String CIA_Name;
	
	protected List<Agency> agencies;
	
	public InsuranceCompany(Long id, String cIA_Name, List<Agency> agencies) {
		super();
		this.id = id;
		CIA_Name = cIA_Name;
		this.agencies = agencies;
	}
	
	public InsuranceCompany() {
		this(0L, "", new ArrayList<Agency>());
	}

	public InsuranceCompany(String cIA_Name, List<Agency> agencies) {
		super();
		CIA_Name = cIA_Name;
		this.agencies = agencies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCIA_Name() {
		return CIA_Name;
	}
	public void setCIA_Name(String cIA_Name) {
		CIA_Name = cIA_Name;
	}
	public List<Agency> getAgencias() {
		return agencies;
	}
	public void setAgencias(List<Agency> agencias) {
		this.agencies = agencias;
	}
	
	
}
