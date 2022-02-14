package project.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "carRepair")
public class CarRepair implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "operation", length = 3)
	protected Long operation;

	@Column(name = "carPlate", length = 7)
	protected String carPlate;

	@Column(name = "model", length = 50)
	protected String model;

	@Column(name = "brandcar", length = 50)
	protected String brandCar;

	@Column(name = "clienteName", length = 50)
	protected String clienteName;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "dateeOrder")
	protected LocalDateTime dateOrder;

	@Column(name = "nor", length = 3)
	protected Long nor;

	@Column(name = "amount", length = 3)
	protected float amount;

	@Column(name = "dateRepair")
	protected LocalDateTime dateRepair;

	@Column(name = "asigPoints", length = 3)
	protected Long asigPoints;

	@Column(name = "repaired")
	protected boolean repaired;

	@JsonIgnoreProperties("myCarRepairs")
	@ManyToOne()
	@JoinColumn(name="id_agency")
	protected Agency myAgency;

	public CarRepair() {
		
	}

	public CarRepair(Long id, Long operation, String carPlate, String model, String brandCar, String clienteName,
			LocalDateTime dateOrder, Long nor, float amount, LocalDateTime dateRepair, Long asigPoints,
			boolean repaired, Agency myAgency) {
		super();
		this.id = id;
		this.operation = operation;
		this.carPlate = carPlate;
		this.model = model;
		this.brandCar = brandCar;
		this.clienteName = clienteName;
		this.dateOrder = dateOrder;
		this.nor = nor;
		this.amount = amount;
		this.dateRepair = dateRepair;
		this.asigPoints = asigPoints;
		this.repaired = repaired;
		this.myAgency = myAgency;
	}

	public CarRepair(Long operation, String carPlate, String model, String brandCar, String clienteName,
			LocalDateTime dateOrder, Long nor, float amount, LocalDateTime dateRepair, Long asigPoints,
			boolean repaired, Agency myAgency) {
		super();
		this.operation = operation;
		this.carPlate = carPlate;
		this.model = model;
		this.brandCar = brandCar;
		this.clienteName = clienteName;
		this.dateOrder = dateOrder;
		this.nor = nor;
		this.amount = amount;
		this.dateRepair = dateRepair;
		this.asigPoints = asigPoints;
		this.repaired = repaired;
		this.myAgency = myAgency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperation() {
		return operation;
	}

	public void setOperation(Long operation) {
		this.operation = operation;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getClienteName() {
		return clienteName;
	}

	public void setClienteName(String clienteName) {
		this.clienteName = clienteName;
	}

	public LocalDateTime getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(LocalDateTime dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Long getNor() {
		return nor;
	}

	public void setNor(Long nor) {
		this.nor = nor;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDateTime getDateRepair() {
		return dateRepair;
	}

	public void setDateRepair(LocalDateTime dateRepair) {
		this.dateRepair = dateRepair;
	}

	public Long getAsigPoints() {
		return asigPoints;
	}

	public void setAsigPoints(Long asigPoints) {
		this.asigPoints = asigPoints;
	}

	public boolean isRepaired() {
		return repaired;
	}

	public void setRepaired(boolean repaired) {
		this.repaired = repaired;
	}

	public Agency getMyAgency() {
		return myAgency;
	}

	public void setMyAgency(Agency myAgency) {
		this.myAgency = myAgency;
	}

}
