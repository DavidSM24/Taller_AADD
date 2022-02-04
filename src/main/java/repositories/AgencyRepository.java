package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import models.Agency;

public interface AgencyRepository extends JpaRepository<Agency,Long>{

	@Query(
			  value = "SELECT * FROM agency limit 10,?", 
			  nativeQuery = true)
	public List<Agency>getAllPaged(int offset);
}