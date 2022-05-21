package onlineshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import onlineshop.model.User;
import onlineshop.model.Shopping;
import onlineshop.model.Product;
import onlineshop.model.Item;
import onlineshop.service.UserService;
import onlineshop.service.ShoppingService;
import onlineshop.service.ProductService;
import onlineshop.service.ItemService;
import onlineshop.utils.AuxiliaryClass;



@Component
public class TestData {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private ItemService itemService;
	
	@PostConstruct
	public void init() {
		
		Product product1 = new Product();
		product1.setName("Patike");
		product1.setQuantity(50);
		product1.setPrice(9000.0);
		product1 = productService.save(product1);
		
		Product product2 = new Product();
		product2.setName("Cipele");
		product2.setQuantity(70);
		product2.setPrice(12000.0);
		product2 = productService.save(product2);
		
		Product product3 = new Product();
		product3.setName("Jakna");
		product3.setQuantity(30);
		product3.setPrice(15000.0);
		product3 = productService.save(product3);
		
		Product product4 = new Product();
		product4.setName("Pantalone");
		product4.setQuantity(20);
		product4.setPrice(5000.0);
		product4 = productService.save(product4);
		
		Product product5 = new Product();
		product5.setName("Kosulja");
		product5.setQuantity(40);
		product5.setPrice(4000.0);
		product5 = productService.save(product5);
		
		Product product6 = new Product();
		product6.setName("Dzemper");
		product6.setQuantity(30);
		product6.setPrice(6000.0);
		product6 = productService.save(product6);
		
		
		User user1 = new User();
		user1.setName("Pera Peric");
		user1.setCity("Novi Sad");
		user1.setAddress("Marka Miljanova 55");
		user1.setJmbg("1004963112233");
		user1.setPhone("064112233");
		user1.setAccountNumber("300-123456-77");
		user1.setAccountBalance(30000.0); 
		user1 = userService.save(user1);

		User user2 = new User();
		user2.setName("Sima Simic");
		user2.setCity("Beograd");
		user2.setAddress("Vojvode Milenka 30");
		user2.setJmbg("1507956112233");
		user2.setPhone("065112233");
		user2.setAccountNumber("300-123456-88");
		user2.setAccountBalance(140000.0); 
		user2 = userService.save(user2);
		
		User user3 = new User();
		user3.setName("Jova Jovic");
		user3.setCity("Nis");
		user3.setAddress("Cara Dusana 45");
		user3.setJmbg("2310974112233");
		user3.setPhone("063112233");
		user3.setAccountNumber("300-123456-22");
		user3.setAccountBalance(130000.0); 
		user3 = userService.save(user3);
		
		
		Shopping shopping1 = new Shopping();
		shopping1.setCode("A 123");
		shopping1.setDateTimeT(java.sql.Timestamp.valueOf("2018-09-23 10:10:10"));
		shopping1.setDateTimeS("23.09.2018. 10:10");
		shopping1.setTotalPrice(51000.0);
		shopping1.setUser(user1);
		shopping1 = shoppingService.save(shopping1);
		
		Shopping shopping2 = new Shopping();
		shopping2.setCode("B 456");
		shopping2.setDateTimeT(java.sql.Timestamp.valueOf("2019-04-15 08:25:20"));
		shopping2.setDateTimeS("15.04.2019. 08:25");
		shopping2.setTotalPrice(51000.0);
		shopping2.setUser(user2);
		shopping2 = shoppingService.save(shopping2);
		
		Shopping shopping3 = new Shopping();
		shopping3.setCode("C 789");
		shopping3.setDateTimeT(AuxiliaryClass.EntriesPresentDateAndTimeSql());
		shopping3.setDateTimeS(AuxiliaryClass.ViewsTextualDateTime(AuxiliaryClass.EntriesPresentDateAndTimeSql()));
		shopping3.setTotalPrice(51000.0);
		shopping3.setUser(user3);
		shopping3 = shoppingService.save(shopping3);

		Item item1 = new Item();
		item1.setProduct(product1);
		item1 = itemService.save(item1);
		
		Item item2 = new Item();
		item2.setProduct(product2);
		item2 = itemService.save(item2);
		
		Item item3 = new Item();
		item3.setProduct(product3);
		item3 = itemService.save(item3);
		
		Item item4 = new Item();
		item4.setProduct(product4);
		item4 = itemService.save(item4);

		Item item5 = new Item();
		item5.setProduct(product5);
		item5 = itemService.save(item5);
		
		Item item6 = new Item();
		item6.setProduct(product6);
		item6 = itemService.save(item6);
		
		shopping1.addItem(item1);
		shopping1.addItem(item2);
		shopping1.addItem(item3);
		shopping1.addItem(item4);
		shopping1.addItem(item5);
		shopping1.addItem(item6);
		shoppingService.save(shopping1);
		
		
		Item item7 = new Item();
		item7.setProduct(product1);
		item7 = itemService.save(item7);
		
		Item item8 = new Item();
		item8.setProduct(product2);
		item8 = itemService.save(item8);
		
		Item item9 = new Item();
		item9.setProduct(product3);
		item9 = itemService.save(item9);
		
		Item item10 = new Item();
		item10.setProduct(product4);
		item10 = itemService.save(item10);

		Item item11 = new Item();
		item11.setProduct(product5);
		item11 = itemService.save(item11);
		
		Item item12 = new Item();
		item12.setProduct(product6);
		item12 = itemService.save(item12);
		
		shopping2.addItem(item7);
		shopping2.addItem(item8);
		shopping2.addItem(item9);
		shopping2.addItem(item10);
		shopping2.addItem(item11);
		shopping2.addItem(item12);
		shoppingService.save(shopping2);
		
		
		
		Item item13 = new Item();
		item13.setProduct(product1);
		item13 = itemService.save(item13);
		
		Item item14 = new Item();
		item14.setProduct(product2);
		item14 = itemService.save(item14);
		
		Item item15 = new Item();
		item15.setProduct(product3);
		item15 = itemService.save(item15);
		
		Item item16 = new Item();
		item16.setProduct(product4);
		item16 = itemService.save(item16);
		
		Item item17 = new Item();
		item17.setProduct(product5);
		item17 = itemService.save(item17);
			
		Item item18 = new Item();
		item18.setProduct(product6);
		item18 = itemService.save(item18);
		
		shopping3.addItem(item13);
		shopping3.addItem(item14);
		shopping3.addItem(item15);
		shopping3.addItem(item16);
		shopping3.addItem(item17);
		shopping3.addItem(item18);
		shoppingService.save(shopping3);

	}

}
