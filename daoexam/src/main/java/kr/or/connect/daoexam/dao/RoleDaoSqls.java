package kr.or.connect.daoexam.dao;

public class RoleDaoSqls {
	public static final String SELECT_ALL = "SELECT role_id, description FROM ROLE ORDER BY ROLE_ID";
	public static final String UPDATE = "UPDATE role set description = :description where role_id = :roleId";
	public static final String SELECT_BY_ROLE_ID = "SELECT role_id, description FROM role where role_id = :roleId";
	public static final String DELECT_BY_ROLE_ID = "DELETE FROM role where role_id = :roleId";
}
