package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Long>{

	@Query(value = "SELECT * FROM gift LIMIT 15 OFFSET :offset")
	public List<Gift>getAllPaged(@Param("offset") int offset);
	
	@Query(value = "SELECT * FROM gift WHERE LOWER(name) LIKE '%:name%' LIMIT 15 OFFSET :offset")
	public List<Gift>getByNamePaged(@Param("name") String name, @Param("offset") int offset);
	
	@Query(value = "SELECT * FROM gift WHERE isAvaliable = :avaliable LIMIT 15 OFFSET :offset")
	public List<Gift> getByAvaliablePaged(@Param("avaliable")boolean avaliable, @Param("offset") int offset);

}