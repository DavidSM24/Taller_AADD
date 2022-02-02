package models;

import java.util.ArrayList;
import java.util.List;

public class Agency {

	protected Long id;
	protected Long zipCode;
	protected String address;
	protected String location;
	protected Long phoneNumber;
	// protected Long repairs
	protected float amount;
	protected Long points;
	protected Long pointsRedeemed;
	protected boolean isActive;
	protected InsuranceCompany myInsurenceCompany;
	protected List<CarRepair> myCarRepairs;
	protected List<ExchangeGift> myExchangesGifts;
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
				+ pointsRedeemed + ", isActive=" + isActive + ", myInsurenceCompany=" + myInsurenceCompany
				+ ", myCarRepairs=" + myCarRepairs + ", myExchangesGifts=" + myExchangesGifts + ", myUser=" + myUser
				+ "]";
	}
}
