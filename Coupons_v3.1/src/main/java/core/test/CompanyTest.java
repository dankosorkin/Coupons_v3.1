package core.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.CompanyService;

@Component
public class CompanyTest {

	private static final String email = "apple@mail";
	private static final String password = "pass1234";

	@Autowired
	private LoginManager manager;

	private CompanyService service;

	public void login() throws CouponSystemException {
		service = (CompanyService) manager.login(email, password, ClientType.COMPANY);
	}

	public void addCoupons() throws CouponSystemException {
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "iPod nano 128Gb", "10% off",
				LocalDate.now(), LocalDate.of(2021, 12, 28), 2, 2.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "iPod nano 256Gb", "10% off",
				LocalDate.now(), LocalDate.of(2021, 12, 28), 10, 3.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "iPod 5th gen 128Gb", "10% off",
				LocalDate.now(), LocalDate.of(2021, 12, 28), 10, 7.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "iPod 5th gen 256Gb", "10% off",
				LocalDate.now(), LocalDate.of(2021, 12, 28), 10, 8.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "iPod 5th gen 512Gb", "10% off",
				LocalDate.of(2021, 02, 01), LocalDate.of(2021, 12, 17), 10, 9.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "Watch series 3 Wi-Fi", "15% off",
				LocalDate.of(2021, 02, 01), LocalDate.of(2021, 12, 17), 10, 5.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "Watch series 3 Wi-Fi + Cellular",
				"10% off", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 7.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "Watch series 4 Wi-Fi", "15% off",
				LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 9.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "Watch series 4 Wi-Fi + Cellular",
				"10% off", LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 7.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "AirPods 2nd gen", "15% off",
				LocalDate.of(2021, 02, 01), LocalDate.of(2021, 02, 17), 10, 2.9, null));
	}

	public void testAll() {
		try {
			login();
			addCoupons();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
