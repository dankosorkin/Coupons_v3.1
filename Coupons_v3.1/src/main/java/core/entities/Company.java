package core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class describes Company entity
 */
@Entity
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Coupon> coupons;

	/** Empty constructor */
	public Company() {
	}

	/**
	 * Constructor creates Company instance
	 * 
	 * @param String name
	 * @param String email
	 * @param String password
	 */
	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Get method returns a company id
	 * 
	 * @return Integer id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set method for a company id. You should use the method to set company id you
	 * get from database.
	 * 
	 * @param Integer id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method returns a company name
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set method for a company name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get method returns a company email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set method for a company email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get method returns a company password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set method for a company password
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get method returns all coupons belong to a company
	 * 
	 * @return List<Coupon> coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	/**
	 * Set method for collection of the coupons belonging to a company
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * The method add new coupon to the company
	 */
	public boolean addCoupon(Coupon coupon) {
		if (this.coupons == null) {
			coupons = new ArrayList<Coupon>();
		}
		coupon.setCompany(this);
		return coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
