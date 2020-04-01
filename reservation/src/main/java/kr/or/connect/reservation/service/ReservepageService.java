package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.ProductPrice;

public interface ReservepageService {
	public Display getDisplayInfo(int productId, int displayInfoId);

	public List<ProductPrice> getProductPriceInfo(int productId);
}
