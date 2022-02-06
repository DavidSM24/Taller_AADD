package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long>{
	
	@Query(value = "SELECT * FROM agency LIMIT 15 OFFSET :offset")
	public List<Agency>getAllPaged(@Param("offset") int offset);
	
	@Query(value = "SELECT a.* FROM agency a "
			+ "INNER JOIN user u IN a.id_user=u.id "
			+ "WHERE LOWER(u.name) LIKE '%:username%'")
	public List<Agency>getByUsernamePaged(String username,int offset);
	
	@Query(value = "SELECT * FROM agency WHERE isActive = :active LIMIT 15 OFFSET :offset")
	public List<Agency> getByActivePaged(boolean active, int offset);
}


/*

SELECT a.* FROM agency a
INNER JOIN user u IN a.id_user=u.id
WHERE LOWER(u.name) LIKE '%:username%'

*/