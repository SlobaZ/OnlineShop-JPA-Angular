package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String city;
	@Column
	private String address;
	@Column
	private String jmbg;
	@Column
	private String phone;
	@Column
	private String accountNumber;
	@Column
	private Double accountBalance;
	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Shopping> shoppings = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	
	
	public List<Shopping> getShoppings() {
		return shoppings;
	}
	public void setShoppings(List<Shopping> shoppings) {
		this.shoppings = shoppings;
	}
	public void addShopping(Shopping shopping) {
		if(shopping.getUser() != this) {
			shopping.setUser(this);
		}
		shoppings.add(shopping);
	}
	
	

}
