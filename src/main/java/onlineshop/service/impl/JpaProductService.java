package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.model.Product;
import onlineshop.repository.ProductRepository;
import onlineshop.service.ProductService;

@Service 
public class JpaProductService implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return productRepository.findAll(pageable);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> saveAll(List<Product> products) {
		return productRepository.saveAll(products); 
	}


	@Override
	public Product delete(Integer id) {
		Product product = productRepository.getById(id);
		if(product != null) {
			productRepository.delete(product);
		}
		return product;
	}

	@Override
	public Page<Product> search(String name, Integer quantity, Double price, int pageNum) {
		if( name != null) {
			name = '%' + name + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return productRepository.search(name, quantity, price, pageable);
	}



}
