package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p WHERE "
			+ "(:name IS NULL or p.name like :name ) AND "
			+ "(:quantity IS NULL OR p.quantity = :quantity) AND "
			+ "(:price IS NULL OR p.price = :price) "
			)
	Page<Product> search(
			@Param("name") String name, 
			@Param("quantity") Integer quantity,
			@Param("price") Double price,
			Pageable pageRequest);
	
	


}
