package core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Company;
import core.exceptions.CouponSystemException;

/**
 * CompanyRepository define custom methods to work with database
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	/**
	 * The method seek in database company instance by its email and password.
	 * 
	 * @param String email
	 * @param String password
	 * @throws CouponSystemException
	 */
	Company findByEmailAndPassword(String email, String password) throws CouponSystemException;

	/**
	 * The method seek in database company instance by its name and password.
	 * 
	 * @param String name
	 * @param String password
	 * @throws CouponSystemException
	 */
	Company findByNameAndEmail(String name, String email) throws CouponSystemException;

	/**
	 * The method seek in database company instance by its email.
	 * 
	 * @param String email
	 * @throws CouponSystemException
	 */
	Company findByEmail(String email);

	/**
	 * The method seek in database company instance by its name.
	 * 
	 * @param String email
	 * @throws CouponSystemException
	 */
	Company findByName(String name);

}
