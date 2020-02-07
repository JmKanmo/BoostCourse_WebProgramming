package kr.or.connect.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.guestbook.dao.GuestBookDao;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.GuestBook;
import kr.or.connect.guestbook.dto.Log;
import kr.or.connect.guestbook.service.GuestBookService;

@Service
public class GuestBookImpl implements GuestBookService {

	private GuestBookDao guestBookDao;
	private LogDao logDao;

	public GuestBookImpl(GuestBookDao guestBookDao, LogDao logDao) {
		this.guestBookDao = guestBookDao;
		this.logDao = logDao;
	}

	@Override
	@Transactional
	public List<GuestBook> getGuestBooks(Integer start) {
		// TODO Auto-generated method stub
		return guestBookDao.selectAll(start, LIMIT);
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteGuestBook(Long id, String ip) {
		// TODO Auto-generated method stub
		int delCount = guestBookDao.deleteById(id);
		Log log = new Log();
		log.setId(id);
		log.setMethod("delete");
		log.setIp(ip);
		log.setRegdate(new Date());
		logDao.insert(log);
		return delCount;
	}

	@Override
	@Transactional(readOnly = false)
	public GuestBook addGuestBook(GuestBook guestBook, String ip) {
		// TODO Auto-generated method stub
		guestBook.setRegDate(new Date());
		Long id = guestBookDao.insert(guestBook);
		guestBook.setId(id);
		Log log = new Log();
		log.setId(guestBook.getId());
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(guestBook.getRegDate());
		logDao.insert(log);
		return guestBook;
	}

	@Override
	@Transactional
	public int getCount() {
		// TODO Auto-generated method stub
		return guestBookDao.selectCount();
	}

}
