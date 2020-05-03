package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReviewWritepageDao;
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
}
