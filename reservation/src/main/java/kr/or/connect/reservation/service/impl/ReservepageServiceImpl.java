package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservepageDao;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.service.ReservepageService;

@Service
public class ReservepageServiceImpl implements ReservepageService {
	@Autowired
	private ReservepageDao reservepageDao;

	@Override
	public Display getDisplayInfo(int productId, int displayInfoId) {
		// TODO Auto-generated method stub
		return reservepageDao.selectDisplayInfo(productId, displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPriceInfo(int productId) {
		// TODO Auto-generated method stub
		return reservepageDao.selectProductPriceInfo(productId);
	}

	@Override
	@Transactional(readOnly = false)
	public int addReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservepageDao.insertReservation(reservation);
	}
}
