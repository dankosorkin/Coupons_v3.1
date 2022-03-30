package core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.repositories.CouponRepository;

@Service
@Transactional
public class HomeService {

	@Autowired
	private CouponRepository repository;

	public List<Coupon> findAll() throws CouponSystemException {
		return this.repository.findAll();
	}

}
