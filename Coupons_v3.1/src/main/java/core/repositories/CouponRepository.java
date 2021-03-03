package core.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	@Query(value = "select distinct c from Coupon c where c.company.id = :id and c.category = :category")
	List<Coupon> findAllByCompanyAndCategory(@Param("id") Integer id, @Param("category") Category category)
			throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific company
	 * in selected price range (from minimum up to selected maximum price)
	 * 
	 * @param Integer id
	 * @param double  price
	 * @throws CouponSystemException
	 */
	@Query(value = "select distinct c from Coupon c where c.company.id = :id and c.price <= :price")
	List<Coupon> findAllByCompanyAndPrice(@Param("id") Integer id, @Param("price") double price)
			throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific customer
	 * and category.
	 * 
	 * @param Integer  id
	 * @param Category category
	 * @throws CouponSystemException
	 */
	@Query(value = "select distinct c from Coupon c inner join c.customers cs where cs.id = :id and c.category = :category")
	List<Coupon> findAllByCustomerAndCategory(@Param("id") Integer id, @Param("category") Category category)
			throws CouponSystemException;

	/**
	 * The method seek in database coupon instances belonging to a specific customer
	 * in selected price range (from minimum up to selected maximum price)
	 * 
	 * @param Integer id
	 * @param double  price
	 * @throws CouponSystemException
	 */
	@Query(value = "select distinct c from Coupon c inner join c.customers cs where cs.id = :id and c.price <= :price")
	List<Coupon> findAllByCustomerAndPrice(@Param("id") Integer id, @Param("price") double price)
			throws CouponSystemException;

}
