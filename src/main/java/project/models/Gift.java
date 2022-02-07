package project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gift implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private  Long id;
	private String name;
	private int points;
	private boolean isAvailable;
	private List<ExchangeGift> exchangeGifts;
	
	
	/**
	 * Construsctor con todos los atributos para usar cuando se obtenga de la base de datos
	 * @param id
	 * @param name
	 * @param points
	 * @param isAvailable
	 * @param exchangeGifts
	 */
	public Gift(Long id, String name, int points, boolean isAvailable, List<ExchangeGift> exchangeGifts) {
		super();
		this.id = id;
		this.name = name;
		this.points = points;
		this.isAvailable = isAvailable;
		this.exchangeGifts = exchangeGifts;
	}
	
	public Gift() {
		this(-1L,"",-1,false,new ArrayList<ExchangeGift>());
	}

	/**
	 * Contructor usado para crear un regalo cuando no este en la base de datos
	 * @param name
	 * @param points
	 * @param isAvailable
	 */
	public Gift(String name, int points, boolean isAvailable) {
		this.id=-1L;
		this.name = name;
		this.points = points;
		this.isAvailable = isAvailable;
		this.exchangeGifts=new ArrayList<ExchangeGift>();
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
				+ ", exchangeGifts=" + exchangeGifts + "]";
	}
	
	
	
	
	
	
		
	

}
