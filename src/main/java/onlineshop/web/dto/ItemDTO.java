package onlineshop.web.dto;

import java.sql.Timestamp;

public class ItemDTO {
	
	private Integer id;
	private Integer quantityItems;
	private Double priceItems;
	
	private Integer shoppingId;
	private String shoppingCode;
	private Double shoppingTotalPrice;
	private Timestamp shoppingDateTimeT;
	private String shoppingDateTimeS;
	
	
	private Integer productId;
	private String productName;
	private Integer productQuantity;
	private Double productPrice;
	
	
	
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
	
	
	
	
	
	public Integer getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}
	public String getShoppingCode() {
		return shoppingCode;
	}
	public void setShoppingCode(String shoppingCode) {
		this.shoppingCode = shoppingCode;
	}
	public Double getShoppingTotalPrice() {
		return shoppingTotalPrice;
	}
	public void setShoppingTotalPrice(Double shoppingTotalPrice) {
		this.shoppingTotalPrice = shoppingTotalPrice;
	}
	public Timestamp getShoppingDateTimeT() {
		return shoppingDateTimeT;
	}
	public void setShoppingDateTimeT(Timestamp shoppingDateTimeT) {
		this.shoppingDateTimeT = shoppingDateTimeT;
	}
	public String getShoppingDateTimeS() {
		return shoppingDateTimeS;
	}
	public void setShoppingDateTimeS(String shoppingDateTimeS) {
		this.shoppingDateTimeS = shoppingDateTimeS;
	}
	
	
	
	
	
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
	

}
