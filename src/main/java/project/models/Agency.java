package project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Table(name = "agency")
public class Agency implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "zipCode", length = 50)
	protected Long zipCode;
	
	@Column(name = "address", length = 50)
	protected String address;
	
	@Column(name = "location", length = 50)
	protected String location;
	
	@Column(name = "phone", length = 9)
	protected Long phoneNumber;
	
	@Column(name = "amount", length = 10)
	protected float amount;
	
	@Column(name = "points", length = 9)
	protected Long points;
	
	@Column(name = "pointsRedeemed", length = 9)
	protected Long pointsRedeemed;
	
	@Column(name = "isActive")
	protected boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_InsuranceCompany")
	protected InsuranceCompany myInsurenceCompany;
	
	@OneToMany(mappedBy = "myAgency", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	protected List<CarRepair> myCarRepairs;
	
	@OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	protected List<ExchangeGift> myExchangesGifts;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id")
	protected User myUser;

	public Agency() {
		this(0L, 0L, "", "", 0L, 0.0f, 0L, 0L, false, new InsuranceCompany(), new ArrayList<CarRepair>(),
				new ArrayList<ExchangeGift>(), new User());
	}

	public Agency(Long id, Long zipCode, String address, String location, Long phoneNumber, float amount, Long points,
			Long pointsRedeemed, boolean isActive, InsuranceCompany myInsurenceCompany, List<CarRepair> myCarRepairs,
			List<ExchangeGift> myExchangesGifts, User myUser) {
		super();
		this.id = id;
		this.zipCode = zipCode;
		this.address = address;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.amount = amount;
		this.points = points;
		this.pointsRedeemed = pointsRedeemed;
		this.isActive = isActive;
		this.myInsurenceCompany = myInsurenceCompany;
		this.myCarRepairs = myCarRepairs;
		this.myExchangesGifts = myExchangesGifts;
		this.myUser = myUser;
	}

	public Agency(Long zipCode, String address, String location, Long phoneNumber, float amount, Long points,
			Long pointsRedeemed, boolean isActive, InsuranceCompany myInsurenceCompany, List<CarRepair> myCarRepairs,
			List<ExchangeGift> myExchangesGifts, User myUser) {
		super();
		this.zipCode = zipCode;
		this.address = address;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.amount = amount;
		this.points = points;
		this.pointsRedeemed = pointsRedeemed;
		this.isActive = isActive;
		this.myInsurenceCompany = myInsurenceCompany;
		this.myCarRepairs = myCarRepairs;
		this.myExchangesGifts = myExchangesGifts;
		this.myUser = myUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Long getPointsRedeemed() {
		return pointsRedeemed;
	}

	public void setPointsRedeemed(Long pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public InsuranceCompany getMyInsurenceCompany() {
		return myInsurenceCompany;
	}

	public void setMyInsurenceCompany(InsuranceCompany myInsurenceCompany) {
		this.myInsurenceCompany = myInsurenceCompany;
	}

	public List<CarRepair> getMyCarRepairs() {
		return myCarRepairs;
	}

	public void setMyCarRepairs(List<CarRepair> myCarRepairs) {
		this.myCarRepairs = myCarRepairs;
	}

	public List<ExchangeGift> getMyExchangesGifts() {
		return myExchangesGifts;
	}

	public void setMyExchangesGifts(List<ExchangeGift> myExchangesGifts) {
		this.myExchangesGifts = myExchangesGifts;
	}

	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Agency other = (Agency) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agency [id=" + id + ", zipCode=" + zipCode + ", address=" + address + ", location=" + location
				+ ", phoneNumber=" + phoneNumber + ", amount=" + amount + ", points=" + points + ", pointsRedeemed="
				+ pointsRedeemed + ", isActive=" + isActive + ", myInsurenceCompany(CIA_Name)=" + myInsurenceCompany.getCIA_Name()
				+ ", myCarRepairs(size)=" + myCarRepairs.size() + ", myExchangesGifts(size)=" + myExchangesGifts.size() + ", myUser(code)=" + myUser.getCode()
				+ "]";
	}
}
