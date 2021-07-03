package core.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class describes Coupon entity
 */
@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer amount;
	private Double price;
	private String image;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = { @JoinColumn(name = "coupon_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
	private List<Customer> customer;

	/** Empty constructor */
	public Coupon() {
	}

	/**
	 * Constructor creates coupon instance
	 * 
	 * @param Company   company
	 * @param Category  category
	 * @param String    title
	 * @param String    description
	 * @param LocalDate startDate
	 * @param LocalDate endDate
	 * @param Integer   amount
	 * @param Double    price
	 * @param String    image
	 */
	public Coupon(Company company, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, Integer amount, Double price, String image) {
		this.company = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * Get method returns a coupon id
	 * 
	 * @return Integer id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set method for a coupon id. You should use the method to set coupon id you
	 * get from database.
	 * 
	 * @param Integer id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method returns a company that a coupon belongs to
	 * 
	 * @return Comapny company
	 */
	public Company getCompany() {
		return this.company;
	}

	/**
	 * Set method for a company that a coupon belongs to
	 * 
	 * @param Company company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Get method returns a coupon category
	 * 
	 * @return Category category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set method for a coupon category
	 * 
	 * @param Category category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Get method returns a coupon title
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set method for a coupon title
	 * 
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get method returns a coupon description
	 * 
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set method for a coupon description
	 * 
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get method return a coupon start date
	 * 
	 * @return LocalDate startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Set method for a coupon start date
	 * 
	 * @param LocalDate startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get method for a coupon end date
	 * 
	 * @return LocalDate endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Set method for a coupon end date
	 * 
	 * @param LocalDate endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get method returns a coupon available amount
	 * 
	 * @return Integer amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Set method for a coupon available amount
	 * 
	 * @param Integer amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * Get method returns a coupon price
	 * 
	 * @return Double price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set method for a coupon price
	 * 
	 * @param Double price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get method for a coupon image URL
	 * 
	 * @return String image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Set method for a coupon image URL
	 * 
	 * @param String image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, endDate, id, price, startDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coupon)) {
			return false;
		}
		Coupon other = (Coupon) obj;
		return category == other.category && Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company_id=" + company.getId() + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
