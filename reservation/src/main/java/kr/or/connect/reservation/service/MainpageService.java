package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

public interface MainpageService {
	public List<Promotion> getPromotion();

	public List<Category> getCategory();

	public List<Product> getProduct(int categoryId, int turn);

	public int getProductCount(int categoryId);
}
