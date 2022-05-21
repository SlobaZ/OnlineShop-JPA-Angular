package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.Shopping;
import onlineshop.model.Product;
import onlineshop.model.Item;
import onlineshop.repository.ProductRepository;
import onlineshop.repository.ItemRepository;
import onlineshop.service.ItemService;

@Service
public class JpaItemService implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Item getById(Integer id) {
		return itemRepository.getById(id);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Page<Item> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return itemRepository.findAll(pageable);
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item delete(Integer id) {
		Item item = itemRepository.getById(id);
		if(item != null) {
			itemRepository.delete(item);
		}
		return item;
	}


	@Override
	public List<Item> findByIdShopping(Integer shoppingId) {
		return itemRepository.findByIdShopping(shoppingId);
	}

	@Override
	public Item buyItem(Integer id, int quantityItem) {
		Item item = itemRepository.getById(id);
		Product product = item.getProduct();
		Shopping shopping = item.getShopping();
		if( product.getQuantity()- quantityItem >= 0   &&  product.getQuantity() >= quantityItem 
			&& shopping.getUser().getAccountBalance() >= (item.getPriceItems() + (product.getPrice()*quantityItem))  ) {
			product.setQuantity( product.getQuantity() - quantityItem ); 
			item.setQuantityItems(item.getQuantityItems() + quantityItem);
			item.setPriceItems(item.getPriceItems() + (product.getPrice()*quantityItem) );
			}
		else {
			return null;
		}
		productRepository.save(product);
		itemRepository.save(item);
		
		return item;
	}

	@Override
	public Item resetItem(Integer id) {
		Item item = itemRepository.getById(id);
		Product product = item.getProduct();
		product.setQuantity( product.getQuantity() + item.getQuantityItems() ); 
		item.setPriceItems(0.0);
		item.setQuantityItems(0);
		productRepository.save(product);
		itemRepository.save(item);
		return item;
	}
	

}
