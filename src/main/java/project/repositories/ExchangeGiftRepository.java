package project.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.ExchangeGift;
@Repository
public interface ExchangeGiftRepository extends JpaRepository<ExchangeGift, Long>{
	
	@Query(value = "SELECT * FROM exchange_gift LIMIT :element OFFSET :page",nativeQuery = true)
	public List<ExchangeGift>getAllPaged(@Param("element") int element,@Param("page") int page );
	
	@Query(value = "SELECT * FROM exchange_gift WHERE is_delivered = :delivered LIMIT :element OFFSET :page",nativeQuery = true)
	public List<ExchangeGift> getByDeliveredPaged(@Param("delivered") boolean delivered, @Param("element") int element, @Param("page") int page);
	
	@Query(value = "SELECT * FROM exchange_gift "
			+ "WHERE id_agency=:id_agency "
			+ "LIMIT :element "
			+ "OFFSET :page",nativeQuery = true)
	public List<ExchangeGift> getByAgencyPaged(@Param("id_agency") int id_agency, @Param("element") int element, @Param("page") int page);

	@Query(value = "SELECT * FROM exchange_gift "
			+ "WHERE CAST(date_exchange AS TEXT) LIKE %:date%",nativeQuery = true)
	public List<ExchangeGift> getByDate(@Param("date") String date);

	@Query(value = "SELECT * FROM exchange_gift AS eg "
			+ "INNER JOIN public.gift AS g ON g.id=eg.id_gift "
			+ "WHERE CAST(g.points AS TEXT) LIKE %:points%",nativeQuery = true)
	public List<ExchangeGift> getByPoints(@Param("points") long points);

	@Query(value = "SELECT * FROM exchange_gift AS eg "
			+ "INNER JOIN public.gift AS g ON g.id=eg.id_gift "
			+ "WHERE LOWER(g.name) LIKE %:gname%",nativeQuery = true)
	public List<ExchangeGift> getByGiftName(@Param("gname") String gname);
}
