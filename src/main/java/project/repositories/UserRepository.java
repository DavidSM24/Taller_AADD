package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT * FROM user LIMIT :element OFFSET :page",nativeQuery = true)
	public List<User> getAllPaged(@Param("element") int element,@Param("page") int page );
	@Query(value = "SELECT * FROM user WHERE user.code = :code",nativeQuery = true)
	public User getByCode(@Param("code") int code );
	@Query(value = "SELECT * FROM user WHERE LOWER(name) LIKE '%:name%'" ,nativeQuery = true)
	public User getByName(@Param("name") String name );
	@Query(value = "SELECT * FROM user WHERE administrator != administrator : LIMIT :element OFFSET :page",nativeQuery = true)
	public List<User> getAllUserAgenciesPaged(@Param("administrator") Boolean administrator, @Param("element") int element,@Param("page") int page );
	@Query(value = "SELECT * FROM user WHERE administrator = administrator : LIMIT :element OFFSET :page",nativeQuery = true)
	public List<User> getAllAdminPaged(@Param("administrator") Boolean administrator, @Param("element") int element,@Param("page") int page );
}
