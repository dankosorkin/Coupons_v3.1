package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Customer;
import core.exceptions.CouponSystemException;

/**
 * CustomerRepository define custom methods to work with database
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	/**
	 * The method seek in database customer instance by its email and password.
	 * 
	 * @param String email
	 * @param String password
	 * @throws CouponSystemException
	 */
	Customer findByEmailAndPassword(String email, String password) throws CouponSystemException;

	/**
	 * The method seek in database customer instance by its email.
	 * 
	 * @param String email
	 * @throws CouponSystemException
	 */
	Customer findByEmail(String email) throws CouponSystemException;

	/**
	 * The method seek in database customer instance by its full name.
	 * 
	 * @param String firstName
	 * @param String lstName
	 * @throws CouponSystemException
	 */
	Customer findByFirstNameAndLastName(String firstName, String lastName) throws CouponSystemException;

}
