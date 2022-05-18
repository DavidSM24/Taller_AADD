package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long>{
	
	@Query(value = "SELECT * FROM agency LIMIT :element OFFSET :page",nativeQuery = true)
	public List<Agency>getAllPaged(@Param("element") int element,@Param("page") int page );

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "INNER JOIN public.user AS u ON a.id_user=u.id "
			+ "WHERE code = :usercode "
			+ "LIMIT 1",nativeQuery = true)
	public Agency getByUserCode(@Param("usercode")Long usercode);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "INNER JOIN public.user AS u ON a.id_user=u.id "
			+ "WHERE LOWER(u.name) LIKE %:username% "
			+ "LIMIT :element OFFSET :page",nativeQuery = true)
	public List<Agency>getByUsernamePaged(@Param("username")String username, @Param("element") int element,@Param("page") int page);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "WHERE LOWER(CAST(points AS TEXT)) LIKE %:points%",nativeQuery = true)
	public List<Agency>getByPoints(@Param("points") int points);
	
	@Query(value = "SELECT * FROM agency WHERE is_active = :active LIMIT :element OFFSET :page",nativeQuery = true)
	public List<Agency> getByActivePaged(@Param("active") boolean active, @Param("element") int element, @Param("page") int page);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "WHERE LOWER(CAST(location AS TEXT)) LIKE %:location%",nativeQuery = true)
	public List<Agency> getByLocation(@Param("location") String location);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "INNER JOIN public.insurance_company AS c ON a.id_insurance_company=c.id "
			+ "WHERE LOWER(CAST(c.CIA_name AS TEXT)) LIKE %:company%",nativeQuery = true)
	public List<Agency> getByCompany(@Param("company") String company);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "WHERE LOWER(CAST(address AS TEXT)) LIKE %:dir%",nativeQuery = true)
	public List<Agency> getByAddress(@Param("dir") String dir);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "WHERE LOWER(CAST(zip_code AS TEXT)) LIKE %:zip%",nativeQuery = true)
	public List<Agency>getByZipcode(@Param("zip") long zip);
}
