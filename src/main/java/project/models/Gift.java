package project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "gift")
public class Gift implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50)	
	private String name;

	@Column(name = "points")
	private int points;

	@Column(name = "isAvaliable")
	private boolean isAvailable;

	@Column(name = "picture", length = 250)
	private String picture;

	@JsonIgnoreProperties("gift")
	@OneToMany(mappedBy = "gift")
	private List<ExchangeGift> exchangeGifts;

	/**
	 * Construsctor c	on todos los atributos para usar cuando se obtenga de la base
	 * de datos
	 * 
	 * @param id
	 * @param name
	 * @param points
	 * @param isAvailable
	 * @param exchangeGifts
	 */
	public Gift(Long id, String name, int points, boolean isAvailable, String picture,
			List<ExchangeGift> exchangeGifts) {
		super();
		this.id = id;
		this.name = name;
		this.points = points;
		this.isAvailable = isAvailable;
		this.picture = picture;
		this.exchangeGifts = exchangeGifts;
	}

	public Gift() {
		this(-1L, "", -1, false, "", new ArrayList<ExchangeGift>());
	}

	/**
	 * Contructor usado para crear un regalo cuando no este en la base de datos
	 * 
	 * @param name
	 * @param points
	 * @param isAvailable
	 */
	public Gift(String name, int points, boolean isAvailable, String picture) {
		this.id = -1L;
		this.name = name;
		this.points = points;
		this.isAvailable = isAvailable;
		this.exchangeGifts = new ArrayList<ExchangeGift>();
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<ExchangeGift> getExchangeGifts() {
		return exchangeGifts;
	}

	public void setExchangeGifts(List<ExchangeGift> exchangeGifts) {
		this.exchangeGifts = exchangeGifts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchangeGifts == null) ? 0 : exchangeGifts.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + points;
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
		Gift other = (Gift) obj;
		if (exchangeGifts == null) {
			if (other.exchangeGifts != null)
				return false;
		} else if (!exchangeGifts.equals(other.exchangeGifts))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (points != other.points)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gift [id=" + id + ", name=" + name + ", points=" + points + ", isAvailable=" + isAvailable
				+ ", exchangeGifts=" + exchangeGifts.size() + "]";
	}

}
