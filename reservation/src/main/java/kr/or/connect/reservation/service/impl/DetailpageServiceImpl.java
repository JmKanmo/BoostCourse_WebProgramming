package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DetailpageDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.DetailpageService;

@Service
public class DetailpageServiceImpl implements DetailpageService {
	private DetailpageDao detailpageDao;

	public DetailpageServiceImpl(DetailpageDao detailpageDao) {
		this.detailpageDao = detailpageDao;
	}

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
}
