package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Reservation;

public interface ReservepageService {
	public Display getDisplayInfo(int productId, int displayInfoId);

	public List<ProductPrice> getProductPriceInfo(int productId);

	public int addReservation(Reservation reservation);
}
