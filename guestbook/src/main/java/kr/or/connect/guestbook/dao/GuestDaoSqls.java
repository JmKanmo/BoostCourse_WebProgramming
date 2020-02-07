package kr.or.connect.guestbook.dao;

public class GuestDaoSqls {
	public static final String SELECT_PAGING = "SELECT id, name, content, regdate FROM guestbook ORDER BY id ASC limit :start,:end";
	public static final String DELETE_BY_ID = "DELETE FROM guestbook WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM guestbook";
}
