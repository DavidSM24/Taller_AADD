package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.ExchangeGift;
@Repository
public interface ExchangeGiftRepository extends JpaRepository<ExchangeGift, Long>{
	
	@Query(value = "SELECT * FROM exchangeGift LIMIT :element OFFSET :page",nativeQuery = true)
	public List<ExchangeGift>getAllPaged(@Param("element") int element,@Param("page") int page );
	
	@Query(value = "SELECT * FROM exchangeGift WHERE isDelivered = :delivered LIMIT :element OFFSET :page",nativeQuery = true)
	public List<ExchangeGift> getByDeliveredPaged(@Param("delivered") boolean delivered, @Param("element") int element, @Param("page") int page);
	
	@Query(value = "SELECT * FROM exchangeGift eg "
			+ "INNER JOIN agency a ON eg.id_agency=a.id "
			+ "INNER JOIN gift g ON eg.id.gift=g.id "
			+ "WHERE eg.id_agency=:id_agency "
			+ "LIMIT :element "
			+ "OFFSET :page",nativeQuery = true)
	public List<ExchangeGift> getByAgencyPaged(@Param("id_agency") int id_agency, @Param("element") int element, @Param("page") int page);
}

/*

SELECT * FROM exchangegift eg
INNER JOIN agency a ON eg.id_agency=a.id
INNER JOIN gift g ON eg.id.gift=g.id
WHERE eg.id_agency=:id_agency
LIMIT :element
OFFSET :page

*/ 