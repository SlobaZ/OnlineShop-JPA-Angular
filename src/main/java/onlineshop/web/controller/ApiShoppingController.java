package onlineshop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.model.User;
import onlineshop.model.Shopping;
import onlineshop.model.Product;
import onlineshop.model.Item;
import onlineshop.service.UserService;
import onlineshop.service.ShoppingService;
import onlineshop.service.ProductService;
import onlineshop.service.ItemService;
import onlineshop.support.ShoppingDTOToShopping;
import onlineshop.support.ShoppingToShoppingDTO;
import onlineshop.utils.AuxiliaryClass;
import onlineshop.web.dto.ShoppingDTO;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/shoppings")
public class ApiShoppingController {
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ShoppingToShoppingDTO toDTO;
	 
	@Autowired
	private ShoppingDTOToShopping toShopping;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemService itemService;
	
	
		

	@GetMapping()
	ResponseEntity<List<ShoppingDTO>> getAllShoppings(
			@RequestParam(required=false) Integer userId, 
			@RequestParam(required=false) String code, 
			@RequestParam(required=false) Double totalPrice, 
			@RequestParam(required=false) String dateTimeBeginning,
			@RequestParam(required=false) String dateTimeEnd, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Shopping> shoppingPage = null;
		
		if(userId!=null || code!=null || totalPrice!=null || dateTimeBeginning!=null || dateTimeEnd!=null) {
			shoppingPage = shoppingService.search(userId,code,totalPrice,dateTimeBeginning,dateTimeEnd,pageNum);
		}
		else {
			shoppingPage = shoppingService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(shoppingPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(shoppingPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<ShoppingDTO> getShoppingById(@PathVariable Integer id){
		Shopping shopping = shoppingService.getById(id);
		if(shopping==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(shopping), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	ResponseEntity<ShoppingDTO> deleteShopping(@PathVariable Integer id){
		Shopping deleted = shoppingService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ShoppingDTO> addShopping( @Valid @RequestBody ShoppingDTO newShoppingDTO){
		if(newShoppingDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		newShoppingDTO.setCode(AuxiliaryClass.AssignCode());
		Shopping savedShopping = shoppingService.save(toShopping.convert(newShoppingDTO));
		
		List<Product> products = productService.findAll();
		for(Product product : products) {
			Item item = new Item();
			item.setProduct(product);
			itemService.save(item);
			savedShopping.addItem(item);
		}
		shoppingService.save(savedShopping);
		return new ResponseEntity<>( toDTO.convert(savedShopping), HttpStatus.CREATED); 
	}


	
	@PutMapping(value="/{id}" , consumes = "application/json")
	public ResponseEntity<ShoppingDTO> editShopping(@PathVariable Integer id , @Valid @RequestBody ShoppingDTO ShoppingDTO ){
		
		Shopping persisted = shoppingService.getById(id);
		persisted.setCode(ShoppingDTO.getCode());
		persisted.setTotalPrice(ShoppingDTO.getTotalPrice());
		persisted.setDateTimeS(ShoppingDTO.getDateTimeS());
		
		User user = userService.getById(ShoppingDTO.getUserId());
		persisted.setUser(user);
		
		shoppingService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	
	@PostMapping(value="/{id}/buy")
	public ResponseEntity<ShoppingDTO> buy( @PathVariable Integer id) {
		
		Shopping shopping = shoppingService.buy(id);
		if(shopping==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>( toDTO.convert(shopping) , HttpStatus.CREATED);
	}
	
	
	

	

}
