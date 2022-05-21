package onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private Integer quantityItems;
	@Column
	private Double priceItems;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "shopping")
	private Shopping shopping;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "product")
	private Product product;
	
	
	public Item() {
		this.quantityItems = 0;
		this.priceItems = 0.0;
	}
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantityItems() {
		return quantityItems;
	}

	public void setQuantityItems(Integer quantityItems) {
		this.quantityItems = quantityItems;
	}

	public Double getPriceItems() {
		return priceItems;
	}

	public void setPriceItems(Double priceItems) {
		this.priceItems = priceItems;
	}
	
	
	

	public Shopping getShopping() {
		return shopping;
	}

	public void setShopping(Shopping shopping) {
		this.shopping = shopping;
		if(!shopping.getItems().contains(this)){
			shopping.getItems().add(this);
		}
	}

	
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		if(!product.getItems().contains(this)){
			product.getItems().add(this);
		}
	}








	
	

}
