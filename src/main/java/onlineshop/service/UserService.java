package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.model.User;

public interface UserService {
	
	User getById(Integer id);
	List<User> findAll();
	Page<User> findAll(int pageNum);
	User save(User user);
	User delete(Integer id);
	
	Page<User> search(
			@Param("name") String name, 
			@Param("city") String city, 
			 int pageNum);
	


}
