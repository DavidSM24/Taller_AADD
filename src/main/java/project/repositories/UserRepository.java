package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT * FROM public.user LIMIT :element OFFSET :page",nativeQuery = true)
	public List<User> getAllPaged(@Param("element") int element,@Param("page") int page );
	@Query(value = "SELECT u.* "
			+ "FROM public.user AS u "
			+ "WHERE u.id NOT IN (SELECT u2.id "
			+ "FROM public.agency AS a "
			+ "INNER JOIN public.user AS u2 ON u2.id=a.id_user "
			+ "WHERE u2.administrator=false);",nativeQuery = true)
	public List<User> getByAvailable();
	@Query(value = "SELECT * FROM public.user WHERE code = :code LIMIT 1",nativeQuery = true)
	public User getByCode(@Param("code") int code );
	@Query(value = "SELECT * FROM public.user WHERE LOWER(name) LIKE %:name% LIMIT 1" ,nativeQuery = true)
	public List<User> getByName(@Param("name") String name );
	@Query(value = "SELECT * FROM public.user WHERE administrator = :administrator LIMIT :element OFFSET :page",nativeQuery = true)
	public List<User> getAllAdminPaged(@Param("administrator") Boolean administrator, @Param("element") int element,@Param("page") int page );

	@Query(value = "SELECT * FROM public.user WHERE LOWER(email) LIKE %:mail%" ,nativeQuery = true)
	public List<User> getByMail(@Param("mail") String mail );

	@Query(value = "SELECT * FROM public.user WHERE CAST(code AS TEXT) LIKE %:fcode%" ,nativeQuery = true)
	public List<User> getByFilterCode(@Param("fcode") String fcode );
}
