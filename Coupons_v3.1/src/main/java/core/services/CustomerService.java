package core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Category;
import core.entities.Coupon;
import core.entities.Customer;
import core.exceptions.CouponSystemException;

/*
 * The class described business logic for system customer methods
 */
@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerService extends ClientService {

	private Integer id;

	/**
	 * Login method check credentials of a customer in a database.
	 * 
	 * @param String email
	 * @param String password
	 * @return boolean
	 * @throws CouponSystemException
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Customer customer = customerRepository.findByEmailAndPassword(email, password);
		if (customer != null) {
			this.id = customer.getId();
			return true;
		}
		throw new CouponSystemException("failed to login");
	}

	/**
	 * The method adds coupon to logged in customer purchase. Method also should
	 * check expiration date; but for the learning purpose and test of the thread
	 * for expired coupons, date check is disabled.
	 * 
	 * @param Coupon coupon
	 * @return Coupon coupon
	 * @throws CouponSystemException
	 * 
	 */
	public boolean purchaseCoupon(Coupon coupon) throws CouponSystemException {

		Coupon couponToPurchase = null;

		Optional<Coupon> optCoupon = couponRepository.findById(coupon.getId());
		if (optCoupon.isPresent()) {
			couponToPurchase = optCoupon.get();
		}

		// check if exists
		if (couponToPurchase == null)
			throw new CouponSystemException("Coupon not found");

		// check coupon amount
		if (couponToPurchase.getAmount() < 1)
			throw new CouponSystemException("Selected coupon is out of stock");

		// check coupon date
//		if (couponToPurchase.getEndDate().isBefore(LocalDate.now()))
//			throw new CouponSystemException("Selected coupon is expired");

		// check customer coupons purchases
		List<Coupon> coupons = loggedInCustomer().getCoupons();

		for (Coupon current : coupons) {
			if (current.equals(couponToPurchase))
				throw new CouponSystemException("You allready bougth this coupon");
		}

		// decrease coupon amount
		couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);

		// add to customer purchases
		loggedInCustomer().addCoupon(couponToPurchase);

		return true;

	}

	/**
	 * The method returns collection of all the coupons belonging to the customer.
	 * 
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllCoupons() throws CouponSystemException {

		List<Coupon> coupons = loggedInCustomer().getCoupons();

		if (coupons.size() > 0)
			return coupons;
		throw new CouponSystemException("The customer have no coupons");
	}

	/**
	 * The method returns collection of all the coupons from specific category
	 * belonging to the customer.
	 * 
	 * @param Category category
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByCategory(Category category) throws CouponSystemException {

		List<Coupon> coupons = couponRepository.findAllByCustomerAndCategory(this.id, category);

		if (coupons.size() > 0)
			return coupons;
		throw new CouponSystemException("The customer have no coupons in selected category");
	}

	/**
	 * The method gets all coupons belonging to a customer by max price.
	 * 
	 * @param double maxPrice
	 * @return List<Coupon> coupons
	 * @throws CouponSystemException
	 * 
	 */
	public List<Coupon> getAllByPrice(double price) throws CouponSystemException {

		List<Coupon> coupons = couponRepository.findAllByCustomerAndPrice(this.id, price);

		if (coupons.size() > 0)
			return coupons;
		throw new CouponSystemException("The customer have no coupons in selected price range");
	}

	/**
	 * The method returns logged in customer.
	 * 
	 * @return Customer customer
	 * @throws CouponSystemException
	 * 
	 */
	public Customer loggedInCustomer() throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(this.id);
		if (opt.isPresent())
			return opt.get();
		throw new CouponSystemException("not logged in");
	}

}
