package core.test;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.repositories.CouponRepository;
import core.services.CustomerService;

@Component
public class CustomerTest {

	private static final String email = "or@mail";
	private static final String password = "or1234";

	@Autowired
	private LoginManager manager;

	@Autowired
	private CouponRepository repository;

	private CustomerService service;

	public void login() throws CouponSystemException {
		service = (CustomerService) manager.login(email, password, ClientType.CUSTOMER);
		System.out.println("========== Customer test ==========");
	}

	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		service.purchaseCoupon(coupon);
		System.out.println(">>> Purchase coupon");
		System.out.println(coupon);
		System.out.println();
	}

	public void getAllCoupons() throws CouponSystemException {
		List<Coupon> coupons = service.getAllCoupons();

		if (coupons != null) {
			System.out.println(">>> All customer coupons");
			for (Coupon coupon : coupons) {
				System.out.println(coupon);
			}
		}

		System.out.println();
	}

	public void getAllByCategory(Category category) throws CouponSystemException {
		List<Coupon> coupons = service.getAllByCategory(category);

		System.out.println(">>> All customer coupons by category: " + category);
		for (Coupon coupon : coupons) {
			System.out.println(coupon);
		}

		System.out.println();
	}

	public void getAllByPrice(double price) throws CouponSystemException {
		List<Coupon> coupons = service.getAllByPrice(price);

		System.out.println(">>> All customer coupons by max price: " + price);
		for (Coupon coupon : coupons) {
			System.out.println(coupon);
		}
	}

	private Coupon couponToPurchase(Integer id) throws CouponSystemException {
		Optional<Coupon> opt = repository.findById(id);
		Coupon coupon = null;
		if (opt.isPresent())
			return opt.get();
		throw new CouponSystemException("");
	}

	public void getCustomerDetails() throws CouponSystemException {
		System.out.println(">>> Customer details");
		System.out.println(service.getDetails());
		System.out.println();
	}

	public void testAll() {
		try {
			login();
			getCustomerDetails();
			purchaseCoupon(couponToPurchase(1));
			// add expired coupon to check thread job
			purchaseCoupon(couponToPurchase(4));
			purchaseCoupon(couponToPurchase(6));
			purchaseCoupon(couponToPurchase(8));
			purchaseCoupon(couponToPurchase(10));
			getAllCoupons();
			getAllByCategory(Category.ELECTRICITY);
			getAllByPrice(8);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
