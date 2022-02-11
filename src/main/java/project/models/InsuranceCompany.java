package project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "insuranceCompany", schema ="public")
public class InsuranceCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "CIA_Name", length = 50)
	private String CIA_Name;
	
	@JsonIgnoreProperties("myInsuranceCompany")
	@OneToMany(mappedBy = "myInsuranceCompany")	
	private List<Agency> agencies;

	public InsuranceCompany(Long id, String cIA_Name, List<Agency> agencies) {
		this.id = id;
		CIA_Name = cIA_Name;
		this.agencies = agencies;
	}

	public InsuranceCompany() {

	}

	public InsuranceCompany(String cIA_Name, List<Agency> agencies) {
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

	public List<Agency> getAgencies() {
		if(agencies==null) agencies=new ArrayList<Agency>();
		return agencies;
	}

	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}

	@Override
	public String toString() {
		return "InsuranceCompany [id=" + id + ", CIA_Name=" + CIA_Name + ", agencies=" + agencies + "]";
	}

	
	
	
}
