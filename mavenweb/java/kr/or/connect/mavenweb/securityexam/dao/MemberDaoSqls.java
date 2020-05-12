package kr.or.connect.mavenweb.securityexam.dao;

public class MemberDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, email, create_date, modify_date FROM member_ WHERE email = :email";
}