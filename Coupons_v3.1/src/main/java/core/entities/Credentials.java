package core.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class describes Company entity
 */
//@Entity
public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;

//	@OneToOne
	private User user;

	/** Empty constructor */
	public Credentials() {
	}

	/**
	 * Constructor creates UserCredentials instance
	 * 
	 * @param String email
	 * @param String password
	 */
	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Get method returns id
	 * 
	 * @return Integer id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set method for id
	 * 
	 * @param Integer id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method returns an email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set method for an email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get method returns a password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Credentials)) {
			return false;
		}
		Credentials other = (Credentials) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password);
	}

}
