package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.MainpageDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.MainpageService;

@Service
public class MainpageServiceImpl implements MainpageService {
	private MainpageDao mainpageDao;

	public MainpageServiceImpl(MainpageDao mainpageDao) {
		this.mainpageDao = mainpageDao;
	}

	@Override
	public List<Promotion> getPromotion() {
		return mainpageDao.selectPromotion();
	}

	@Override
	public List<Category> getCategory() {
		// TODO Auto-generated method stub
		return mainpageDao.selectCategory();
	}

	@Override
	public List<Product> getProduct(int categoryId, int turn, int limit) {
		// TODO Auto-generated method stub
		return mainpageDao.selectProduct(categoryId, turn, limit);
	}

	@Override
	public int getProductCount(int categoryId) {
		// TODO Auto-generated method stub
		return mainpageDao.getProductCount(categoryId);
	}
}
