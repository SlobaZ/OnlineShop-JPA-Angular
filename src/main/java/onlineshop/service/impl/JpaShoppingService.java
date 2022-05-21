package onlineshop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.User;
import onlineshop.model.Shopping;
import onlineshop.model.Item;
import onlineshop.repository.UserRepository;
import onlineshop.repository.ShoppingRepository;
import onlineshop.repository.ItemRepository;
import onlineshop.service.ShoppingService;
import onlineshop.utils.AuxiliaryClass;

@Service
public class JpaShoppingService implements ShoppingService{
	
	@Autowired
	private ShoppingRepository shoppingRepository; 
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Shopping getById(Integer id) {
		return shoppingRepository.getById(id);
	}

	@Override
	public List<Shopping> findAll() {
		return shoppingRepository.findAll();
	}
	
	@Override
	public Page<Shopping> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return shoppingRepository.findAll(pageable);
	}

	@Override
	public Shopping save(Shopping shopping) {
		return shoppingRepository.save(shopping);
	}

	@Override
	public Shopping delete(Integer id) {
		Shopping shopping = shoppingRepository.getById(id);
		if(shopping != null) {
			shoppingRepository.delete(shopping);
		}
		return shopping;
	}

	
	@Override
	public Page<Shopping> search(Integer userId, String code, Double totalPrice, 
			String dateTimeBeginning, String dateTimeEnd, int pageNum) {
		
		Timestamp dateTimeBeginningT = null;
		Timestamp dateTimeEndT = null;
		if( code != null) {
			code = '%' + code + '%';
		}
		if(dateTimeBeginning != null) { 
		dateTimeBeginningT = AuxiliaryClass.ConvertStringToSqlDateAndTime(dateTimeBeginning);
		}
		if(dateTimeEnd !=null) {
			 dateTimeEndT = AuxiliaryClass.ConvertStringToSqlDateAndTime(dateTimeEnd);
		}
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return shoppingRepository.search(userId, code, totalPrice, dateTimeBeginningT, dateTimeEndT,  pageable);
	}

	
	@Override
	public Shopping buy(Integer id) {
		Shopping shopping = shoppingRepository.getById(id);
		List<Item> items = itemRepository.findByIdShopping(id);
		Double	x = 0.0 ;
		for (Item item: items) {
			x += item.getPriceItems();
		  }
		if(shopping.getUser().getAccountBalance()<x) {
			return null;
		}
		shopping.setTotalPrice(x);
		User user = shopping.getUser();
		user.setAccountBalance(user.getAccountBalance()-x); 
		userRepository.save(user);
		shoppingRepository.save(shopping);
		return shopping;
	}




}
