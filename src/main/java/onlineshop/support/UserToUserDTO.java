package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.model.User;
import onlineshop.web.dto.UserDTO;


@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		if(user==null){
			return null;
		}
		
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setCity(user.getCity());
		dto.setAddress(user.getAddress());
		dto.setJmbg(user.getJmbg());
		dto.setPhone(user.getPhone());
		dto.setAccountNumber(user.getAccountNumber());
		dto.setAccountBalance(user.getAccountBalance());
		
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> ret = new ArrayList<>();
		
		for(User u: users){
			ret.add(convert(u));
		}
		
		return ret;
	}
}
