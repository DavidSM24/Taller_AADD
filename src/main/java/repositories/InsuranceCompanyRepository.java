package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.InsuranceCompany;
@Repository
public interface InsuranceCompanyRepository  extends JpaRepository<InsuranceCompany, Long>{
	List<InsuranceCompany> getByNamePaged(String name);
	List<InsuranceCompany> getByCIAName(String name);

}
