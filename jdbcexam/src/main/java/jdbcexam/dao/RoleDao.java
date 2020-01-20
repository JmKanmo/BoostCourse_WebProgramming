package jdbcexam.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import jdbcexam.dto.Role;

public class RoleDao {
	private String password = "connect123!@#";
	private String user = "connectuser";
	private String url = "jdbc:mysql://localhost:3306/connectDB";

	public Role getRole(Integer roleId) {
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			String query = "SELECT role_id,description from role where role_id = ?";
			ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, roleId);
			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(2);
				role = new Role(id, description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return role;
	}

	public int insertRole(Role role) {
		Connection conn = null;
		PreparedStatement ps = null;
		int insertCount = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			String query = "INSERT INTO role(role_id,description) VALUES(?,?)";
			ps = (PreparedStatement) conn.prepareStatement(query);
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			insertCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return insertCount;
	}

	public void updateRole(int role_id, String description) {
		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("update role set description = ? where role_id = ?");) {
			ps.setString(1, description);
			ps.setInt(2, role_id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteRole(int role_id) {
		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("delete from role where role_id = ?");) {

			ps.setInt(1, role_id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Role> getRoles() {
		List<Role> roles = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("SELECT* from role order by role_id desc");) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					roles.add(new Role(rs.getInt(1), rs.getString(2)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roles;
	}
}
