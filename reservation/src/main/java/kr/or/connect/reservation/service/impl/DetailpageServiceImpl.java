package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DetailpageDao;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Review;
import kr.or.connect.reservation.dto.ReviewAvgCnt;
import kr.or.connect.reservation.service.DetailpageService;

@Service
public class DetailpageServiceImpl implements DetailpageService {
	@Autowired
	private DetailpageDao detailpageDao;

	@Override
	public List<Product> getProduct(int id) {
		// TODO Auto-generated method stub
		return detailpageDao.selectProduct(id);
	}

	@Override
	public List<Promotion> getPromotion(int productId) {
		// TODO Auto-generated method stub
		return detailpageDao.selectPromotion(productId);
	}

	@Override
	public int getEtcImageCount(int productId) {
		// TODO Auto-generated method stub
		return detailpageDao.getEtcImageCount(productId);
	}

	@Override
	public ReviewAvgCnt getReviewAvgCnt(int productId) {
		// TODO Auto-generated method stub
		return detailpageDao.getUserReviewAvgCnt(productId);
	}

	@Override
	public List<Review> getUserReview(int productId) {
		// TODO Auto-generated method stub
		return detailpageDao.selectReview(productId);
	}

	@Override
	public Display getDisplayInfo(int displayInfoId) {
		// TODO Auto-generated method stub
		return detailpageDao.selectDisplayInfo(displayInfoId);
	}
}
