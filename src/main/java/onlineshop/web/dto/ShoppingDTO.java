package onlineshop.web.dto;

import java.sql.Timestamp;


public class ShoppingDTO {
	
	
	private Integer id;
	private String code;
	private Double totalPrice;
	private Timestamp dateTimeT;
	private String dateTimeS;
	
	private Integer userId;
	private String userName;
	private Double userAccountBalance;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Timestamp getDateTimeT() {
		return dateTimeT;
	}
	public void setDateTimeT(Timestamp dateTimeT) {
		this.dateTimeT = dateTimeT;
	}
	public String getDateTimeS() {
		return dateTimeS;
	}
	public void setDateTimeS(String dateTimeS) {
		this.dateTimeS = dateTimeS;
	}
	
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getUserAccountBalance() {
		return userAccountBalance;
	}
	public void setUserAccountBalance(Double userAccountBalance) {
		this.userAccountBalance = userAccountBalance;
	}
	
	

}
