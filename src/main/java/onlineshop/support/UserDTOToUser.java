package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.User;
import onlineshop.repository.UserRepository;
import onlineshop.web.dto.UserDTO;



@Component
public class UserDTOToUser implements Converter<UserDTO, User> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User convert(UserDTO dto) {
		User user = null;
		
		if(dto.getId()!=null){
			user = userRepository.getById(dto.getId());
			
			if(user == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant User");
			}
		}
		else {
			user = new User();
		}
		
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setCity(dto.getCity());
		user.setAddress(dto.getAddress());
		user.setJmbg(dto.getJmbg());
		user.setPhone(dto.getPhone());
		user.setAccountNumber(dto.getAccountNumber());
		user.setAccountBalance(dto.getAccountBalance());
		
		return user;
	}
	
	public List<User> convert (List<UserDTO> dtoUsers){
		List<User> users = new ArrayList<>();
		
		for(UserDTO dto : dtoUsers){
			users.add(convert(dto));
		}
		
		return users;
	}
}
