package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Agency;

public interface AgencyRepository extends JpaRepository<Agency,Long>{

	public List<Agency>getAllPaged(int offset, int page);
}