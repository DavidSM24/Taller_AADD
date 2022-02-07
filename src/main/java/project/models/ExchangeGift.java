package project.models;

import java.time.LocalDate;

public class ExchangeGift {

	protected Long id;
	protected LocalDate dateExchange;
	protected String observations;
	protected boolean isDelivered;
	protected Agency agency;
	protected Gift gift;
	
	public ExchangeGift() {
		this(0L,LocalDate.now(),"",false,new Agency(),new Gift());
	}
	
	public ExchangeGift(Long id, LocalDate dateExchange, String observations, boolean isDelivered, Agency agency,
			Gift gift) {
		super();
		this.id = id;
		this.dateExchange = dateExchange;
		this.observations = observations;
		this.isDelivered = isDelivered;
		this.agency = agency;
		this.gift = gift;
	}

	public ExchangeGift(LocalDate dateExchange, String observations, boolean isDelivered, Agency agency, Gift gift) {
		super();
		this.dateExchange = dateExchange;
		this.observations = observations;
		this.isDelivered = isDelivered;
		this.agency = agency;
		this.gift = gift;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateExchange() {
		return dateExchange;
	}

	public void setDateExchange(LocalDate dateExchange) {
		this.dateExchange = dateExchange;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
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
		ExchangeGift other = (ExchangeGift) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExchangeGift [id=" + id + ", dateExchange=" + dateExchange + ", observations=" + observations
				+ ", isDelivered=" + isDelivered + ", agency=" + agency + ", gift=" + gift + "]";
	}
}
