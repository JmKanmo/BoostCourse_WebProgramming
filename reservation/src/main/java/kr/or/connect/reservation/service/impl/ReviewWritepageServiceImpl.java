package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReviewWritepageDao;
import kr.or.connect.reservation.dto.ReviewData;
import kr.or.connect.reservation.service.ReviewWritepageService;

@Service
public class ReviewWritepageServiceImpl implements ReviewWritepageService {
	@Autowired
	ReviewWritepageDao reviewWritepageDao;

	@Override
	public String selectImage(int imageId) {
		// TODO Auto-generated method stub
		return reviewWritepageDao.selectImage(imageId);
	}

	@Override
	@Transactional(readOnly = false)
	public void insertReviewData(ReviewData reviewData, boolean isEmpty) {
		if (isEmpty) {
			reviewWritepageDao.insertReservationUserComment(reviewData);
		} else {
			reviewWritepageDao.insertReservationUserCommentImage(reviewData.getReservationId(),
					reviewWritepageDao.insertReservationUserComment(reviewData),
					reviewWritepageDao.insertFileInfo(reviewData));
		}
	}
}
