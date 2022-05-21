package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.model.User;
import onlineshop.model.Shopping;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE "
			+ "(:name IS NULL or u.name like :name ) AND "
			+ "(:city IS NULL OR u.city like :city) "
			)
	Page<User> search(
			@Param("name") String name, 
			@Param("city") String city,
			Pageable pageRequest);
	
	
	
	@Query("SELECT s FROM Shopping s WHERE s.user.id = :idU")
	Shopping userData( @Param("idU") Integer idU);

}
