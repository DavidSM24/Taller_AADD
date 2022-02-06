package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.InsuranceCompany;
@Repository
public interface InsuranceCompanyRepository  extends JpaRepository<InsuranceCompany, Long>{
	@Query(value="SELECT * FROM insuranceCompany LIMIT 15 OFFSET :offset")
	List<InsuranceCompany> getAllPaged(@Param("offset") int offset);
	@Query(value = "SELECT * FROM insuranceCompany WHERE LOWER(name) LIKE '%:name%' LIMIT 15 OFFSET :offset")
	List<InsuranceCompany> getByNamePaged(@Param("name") String name, @Param("offset") int offset);
	@Query(value = "SELECT * FROM insuranceCompany WHERE LOWER(name) LIKE '%:name%'")
	List<InsuranceCompany> getByCIAName(@Param("name") String name);

}
