package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.User;
import onlineshop.repository.UserRepository;
import onlineshop.service.UserService;


@Service
public class JpaUserService implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return userRepository.findAll(pageable);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(Integer id) {
		User user = userRepository.getById(id);
		if(user != null) {
			userRepository.delete(user);
		}
		return user;
	}

	@Override
	public Page<User> search(String name, String city, int pageNum) {
		if( name != null) {
			name = '%' + name + '%';
		}
		if(city != null) {
			city = '%' + city + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return userRepository.search(name, city, pageable);
	}



}
