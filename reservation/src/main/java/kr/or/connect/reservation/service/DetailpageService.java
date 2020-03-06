package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Review;
import kr.or.connect.reservation.dto.ReviewAvgCnt;

public interface DetailpageService {
	public List<Product> getProduct(int id);

	public List<Promotion> getPromotion(int productId);

	public int getEtcImageCount(int productId);

	public ReviewAvgCnt getReviewAvgCnt(int productId);
	
	public List<Review> getUserReview(int productId);
}
