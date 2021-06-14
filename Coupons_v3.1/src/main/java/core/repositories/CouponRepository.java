package core.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;

/**
 * CouponRepository define custom methods to work with database
 */
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	/**
	 * The method seek in database coupon instance by its title.
	 * 
	 * @param String title
	 * @throws CouponSystemException
	 */
	Coupon findByTitle(String title) throws CouponSystemException;

	/**
	 * The method seek in database coupon instance by its id and customer id.
	 * 
	 * @param Integer couponId
	 * @param Integer customerId
	 * @throws CouponSystemException
	 */
	Coupon findByIdAndCustomerId(Integer couponId, Integer customerId) throws CouponSystemException;

	/**
	 * The method seek in database expired coupon instances .
	 * 
	 * @param LocalDate date
	 * @throws CouponSystemException
	 */
	List<Coupon> findAllByEndDateBefore(LocalDate date) throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific company
	 * and category.
	 * 
	 * @param Integer  id
	 * @param Category category
	 * @throws CouponSystemException
	 */
	List<Coupon> findAllByCompanyIdAndCategory(Integer id, Category category) throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific company
	 * in selected price range (from minimum up to selected maximum price)
	 * 
	 * @param Integer id
	 * @param double  price
	 * @throws CouponSystemException
	 */
	List<Coupon> findAllByCompanyIdAndPriceLessThanEqual(Integer id, double price) throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific customer
	 * and category.
	 * 
	 * @param Integer  id
	 * @param Category category
	 * @throws CouponSystemException
	 */
	List<Coupon> findAllByCustomerIdAndCategory(Integer id, Category category) throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific customer
	 * in selected price range (from minimum up to selected maximum price)
	 * 
	 * @param Integer id
	 * @param double  price
	 * @throws CouponSystemException
	 */
	List<Coupon> findAllByCustomerIdAndPriceLessThanEqual(Integer id, double price) throws CouponSystemException;

}
