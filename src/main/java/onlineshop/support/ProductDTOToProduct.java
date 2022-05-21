package onlineshop.support;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.web.dto.ProductDTO;
import onlineshop.model.Product;
import onlineshop.service.ProductService;




@Component
public class ProductDTOToProduct implements Converter<ProductDTO, Product> {
	
	@Autowired
	private ProductService productService;

	@Override
	public Product convert(ProductDTO dto) {
		Product product = null;
		
		if(dto.getId()!=null){
			product = productService.getById(dto.getId());
			
			if(product == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant Product");
			}
		}
		else {
			product = new Product();
		}
		
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setQuantity(dto.getQuantity());
		product.setPrice(dto.getPrice());
		
		return product;
	}
	
	public List<Product> convert (List<ProductDTO> dtoProducts){
		List<Product> products = new ArrayList<>();
		
		for(ProductDTO dto : dtoProducts){
			products.add(convert(dto));
		}
		
		return products;
	}

}
