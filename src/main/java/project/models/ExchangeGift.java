package project.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "exchangeGift")
public class ExchangeGift implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;
	@Column(name = "dateExchange")
	protected LocalDate dateExchange;
	@Column(name = "observations", length = 300)
	protected String observations;
	@Column(name = "isDelivered")
	protected boolean isDelivered;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_agency")
	protected Agency agency;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_gift")
	protected Gift gift;

	public ExchangeGift() {
		this(0L, LocalDate.now(), "", false, new Agency(), new Gift());
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