package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.web.dto.ShoppingDTO;
import onlineshop.model.User;
import onlineshop.repository.UserRepository;
import onlineshop.model.Shopping;

import onlineshop.service.ShoppingService;
import onlineshop.utils.AuxiliaryClass;


@Component
public class ShoppingDTOToShopping implements Converter<ShoppingDTO, Shopping>{

	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Shopping convert(ShoppingDTO shoppingDTO) {
		
		User user = userRepository.getById(shoppingDTO.getUserId());
		if(user!=null) {
		Shopping shopping = null;
		
		if(shoppingDTO.getId() != null){
			shopping = shoppingService.getById(shoppingDTO.getId());

		}
		else {
			shopping = new Shopping();
		}
					
			shopping.setId(shoppingDTO.getId());
			shopping.setCode(shoppingDTO.getCode());
			shopping.setTotalPrice(0.0);
			if(shoppingDTO.getDateTimeT()==null) {
				shopping.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
				shopping.setDateTimeS(AuxiliaryClass.ViewsTextualDateTime(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
			}
			if(shoppingDTO.getDateTimeT()!=null) {
				shopping.setDateTimeT(AuxiliaryClass.ConvertStringToSqlDateAndTime(shoppingDTO.getDateTimeS()));
				shopping.setDateTimeS(shoppingDTO.getDateTimeS());
			}
			shopping.setUser(user);
			return shopping;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	

	public List<Shopping> convert(List<ShoppingDTO> shoppingDTOs){
		List<Shopping> ret = new ArrayList<>();
		
		for(ShoppingDTO shoppingDTO : shoppingDTOs){
			ret.add(convert(shoppingDTO));
		}
		
		return ret;
	}
}
