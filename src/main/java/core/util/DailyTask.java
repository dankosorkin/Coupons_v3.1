package core.util;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.repositories.CouponRepository;

@Component
public class DailyTask extends Thread {

	private boolean quit;

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void startTask() {
		this.start();
		System.out.println(" <<<<<<<<<< Daily task start >>>>>>>>>>");
	}

	/**
	 * The method defines thread action to delete expired coupons from database.
	 * Current version runs after 5 seconds, real version should make this operation
	 * once in a day (24 * 60 * 60 * 1000).
	 */
	@Override
	@Transactional
	public void run() {
		while (!quit) {
			try {
				Thread.sleep(24 * 60 * 60 * 1000);
				List<Coupon> coupons = repository.findAllByEndDateBefore(LocalDate.now());
				System.out.println("********** Expired coupons **********");
				for (Coupon coupon : coupons) {
					System.out.println(coupon);
					repository.delete(coupon);
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	@PreDestroy
	public void stopTask() {
		this.quit = true;
		this.interrupt();
		System.out.println(" <<<<<<<<<< Daily task ended >>>>>>>>>>");
	}

}
