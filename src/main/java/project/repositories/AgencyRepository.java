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
			+ "WHERE usercode = :usercode "
			+ "LIMIT 1",nativeQuery = true)
	public Agency getByUserCode(@Param("usercode")Long usercode);

	@Query(value = "SELECT a.* FROM agency AS a "
			+ "INNER JOIN public.user AS u ON a.id_user=u.id "
			+ "WHERE LOWER(u.name) LIKE %:username% "
			+ "LIMIT :element OFFSET :page",nativeQuery = true)
	public List<Agency>getByUsernamePaged(@Param("username")String username, @Param("element") int element,@Param("page") int page);
	
	@Query(value = "SELECT * FROM agency WHERE is_active = :active LIMIT :element OFFSET :page",nativeQuery = true)
	public List<Agency> getByActivePaged(@Param("active") boolean active, @Param("element") int element, @Param("page") int page);
}
