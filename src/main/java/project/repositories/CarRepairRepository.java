package project.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.CarRepair;
import project.models.ExchangeGift;

@Repository
public interface CarRepairRepository extends JpaRepository<CarRepair, Long> {
	@Query(value = "SELECT * FROM car_repair LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getAllPaged(@Param("element") int element, @Param("paged") int paged);

	@Query(value = "SELECT * " +
			"FROM car_repair " +
			"WHERE car_plate = :operation LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByIdOperationPaged(@Param("operation") Long operation, @Param("element") int nElement,
			@Param("paged") int paged);

	@Query(value = "SELECT * " +
			"FROM car_repair " +
			"WHERE CAST(car_plate AS TEXT) LIKE %:operation%", nativeQuery = true)
	public List<CarRepair> getByIdOperationFilter(@Param("operation") String operation);

	@Query(value = "SELECT * FROM car_repair WHERE LOWER(car_plate) LIKE %:carPlate% LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByCarPlatePaged(@Param("carPlate") String carPlate, @Param("element") int nElement,
			@Param("paged") int paged);

	@Query(value = "SELECT * FROM car_repair WHERE LOWER(cliente_name) LIKE %:name% LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByClientNamePaged(@Param("name") String name, @Param("element") int nElement,
			@Param("paged") int paged);

	@Query(value = "SELECT * FROM  car_repair WHERE date_repair BETWEEN :ini AND :end LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByDateOrderPaged(@Param("ini") LocalDateTime ini, @Param("end") LocalDateTime end,
			@Param("element") int nELement, @Param("paged") int paged);

	@Query(value = "SELECT * FROM car_repair WHERE asig_points BETWEEN :min AND :max LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByPointsPaged(@Param("min") int min, @Param("max") int max,
			@Param("element") int nElement, @Param("paged") int paged);

	@Query(value = "SELECT * FROM car_repair WHERE repaired =:repaired LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByStatePaged(@Param("repaired") boolean repaired, @Param("element") int nElement,
			@Param("paged") int paged);
	
	@Query(value = "SELECT * FROM car_repair WHERE id_agency=:id_agency LIMIT :element OFFSET :paged", nativeQuery = true)
	public List<CarRepair> getByAgencyPaged(@Param("id_agency") int id_agency, @Param("element") int nElement,
			@Param("paged") int paged);
	

}
