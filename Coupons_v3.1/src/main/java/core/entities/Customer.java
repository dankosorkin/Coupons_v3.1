package core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Class describes Customer entity
 */
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = {
			@JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "coupon_id") })
	private List<Coupon> coupons;

	/** Empty constructor */
	public Customer() {
	}

	/**
	 * Constructor creates Customer instance
	 * 
	 * @param String firstName
	 * @param String lastName
	 * @param String email
	 * @param String password
	 */
	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Get method returns a customer id
	 * 
	 * @return Integer id;
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set method for a customer id. You should use the method to set a customer id
	 * you get from database.
	 * 
	 * @param int id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method returns a customer first name
	 * 
	 * @return String firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set method for a customer first name
	 * 
	 * @param String firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get method returns a customer last name
	 * 
	 * @return String lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set method for a customer last name
	 * 
	 * @param String lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get method returns a customer email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set method for a customer email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get method returns a customer password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set method for a customer password
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get method returns all coupons belongs to a customer
	 * 
	 * @return List<Coupon> coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * Set method for a collection of coupons belonging to a customer
	 * 
	 * @param List<Coupon> coupons
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public boolean addCoupon(Coupon coupon) {
		if (this.coupons == null) {
			coupons = new ArrayList<Coupon>();
		}
		return coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
