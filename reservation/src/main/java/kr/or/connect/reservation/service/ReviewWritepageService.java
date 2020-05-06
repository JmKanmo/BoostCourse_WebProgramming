package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReviewData;

public interface ReviewWritepageService {
	public String selectImage(int imageId);

	public void insertReviewData(ReviewData reviewData, boolean isEmpty);
}
