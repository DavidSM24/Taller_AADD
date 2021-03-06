package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.InsuranceCompany;
//modificado por David
@Repository
public interface InsuranceCompanyRepository  extends JpaRepository<InsuranceCompany, Long>{
	@Query(value="SELECT * FROM insurance_company LIMIT :limit OFFSET :offset",nativeQuery = true)
	List<InsuranceCompany> getAllPaged(@Param("limit")int limit,@Param("offset") int offset);
	@Query(value = "SELECT * FROM insurance_company WHERE LOWER(cia_name) LIKE %:name% LIMIT :limit OFFSET :offset",nativeQuery = true)
	List<InsuranceCompany> getByNamePaged(@Param("name") String name,@Param("limit") int limit, @Param("offset") int offset);
	@Query(value = "SELECT * FROM insurance_company WHERE LOWER(cia_name) LIKE %:name%",nativeQuery = true)
	List<InsuranceCompany> getByCIAName(@Param("name") String name);

}
