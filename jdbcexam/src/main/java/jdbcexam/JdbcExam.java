package jdbcexam;

import jdbcexam.dao.RoleDao;

public class JdbcExam {

	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
//		System.out.println(dao.insertRole(new Role(103, "Nebiros")));
//		System.out.println(dao.getRoles());
//		dao.updateRole(103, "추워죽겟다ㅠㅠ");
		dao.deleteRole(103);
	}

}
