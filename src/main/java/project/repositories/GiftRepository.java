package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Long>{

	@Query(value = "SELECT * FROM gift LIMIT :element OFFSET :page", nativeQuery = true)
	public List<Gift>getAllPaged(@Param("element") int element,@Param("page") int page);
	
	@Query(value = "SELECT * FROM gift WHERE LOWER(name) LIKE %:name% LIMIT :element OFFSET :page", nativeQuery = true)
	public List<Gift>getByNamePaged(@Param("name") String name, @Param("element") int element,@Param("page") int page);
	
	@Query(value = "SELECT * FROM gift WHERE is_avaliable = :avaliable LIMIT :element OFFSET :page", nativeQuery = true)
	public List<Gift> getByAvaliablePaged(@Param("avaliable")boolean avaliable, @Param("element") int element,@Param("page") int page);

	@Query(value = "SELECT * FROM gift WHERE CAST(points AS TEXT) LIKE '%:points%'", nativeQuery = true)
	public List<Gift> getByPoints(@Param("points")int points);

}