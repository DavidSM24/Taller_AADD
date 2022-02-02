package models;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCompany {
	protected Long id;
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
