package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.model.Product;


public interface ProductService {

	Product getById(Integer id);
	List<Product> findAll();
	Page<Product> findAll(int pageNum);
	Product save(Product product);
	List<Product> saveAll(List<Product> products);
	Product delete(Integer id);
	
	Page<Product> search(
			@Param("name") String name, 
			@Param("quantity") Integer quantity,
			@Param("price") Double price,
			 int pageNum);
	
	

}
