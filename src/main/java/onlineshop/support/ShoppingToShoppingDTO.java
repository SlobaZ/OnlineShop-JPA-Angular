package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.web.dto.ShoppingDTO;
import onlineshop.model.Shopping;
import onlineshop.utils.AuxiliaryClass;


@Component
public class ShoppingToShoppingDTO implements Converter<Shopping, ShoppingDTO>{

	@Override
	public ShoppingDTO convert(Shopping shopping) {
		
		ShoppingDTO retValue = new ShoppingDTO();
		
		retValue.setId(shopping.getId());
		retValue.setCode(shopping.getCode());
		retValue.setTotalPrice(shopping.getTotalPrice());
		if(shopping.getDateTimeT()==null) {
			retValue.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
			retValue.setDateTimeS(AuxiliaryClass.ViewsTextualDateTime(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
		}
		else {
			retValue.setDateTimeT(AuxiliaryClass.ConvertStringToSqlDateAndTime(shopping.getDateTimeS()));
			retValue.setDateTimeS(shopping.getDateTimeS());
		}
		
		retValue.setUserId(shopping.getUser().getId());
		retValue.setUserName(shopping.getUser().getName());
		retValue.setUserAccountBalance(shopping.getUser().getAccountBalance());

		return retValue;
	}

	public List<ShoppingDTO> convert(List<Shopping> shoppings){
		List<ShoppingDTO> ret = new ArrayList<>();
		
		for(Shopping shopping : shoppings){
			ret.add(convert(shopping));
		}
		
		return ret;
	}

}
