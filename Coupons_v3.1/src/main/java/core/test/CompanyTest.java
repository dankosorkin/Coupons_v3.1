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
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player1", "128Gb capacity",
				LocalDate.now(), LocalDate.of(2022, 12, 28), 2, 5.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player2", "256Gb capacity",
				LocalDate.now(), LocalDate.of(2022, 12, 28), 10, 6.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player3", "512Gb capacity",
				LocalDate.now(), LocalDate.of(2022, 12, 28), 10, 7.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player4", "1Tb capacity",
				LocalDate.now(), LocalDate.of(2022, 12, 28), 10, 8.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player5", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 12, 17), 10, 9.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player6", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 12, 17), 10, 6.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player7", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 02, 17), 10, 9.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player8", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 02, 17), 10, 9.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player9", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 02, 17), 10, 7.9, null));
		service.addCoupon(new Coupon(service.getDetails(), Category.ELECTRICITY, "MP3 player10", "2Tb capacity",
				LocalDate.of(2021, 02, 01), LocalDate.of(2022, 02, 17), 10, 2.9, null));
	}

}
