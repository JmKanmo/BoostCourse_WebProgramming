package Todo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TodoDao {
	private String password = "connect123!@#";
	private String user = "connectuser";
	private String url = "jdbc:mysql://localhost:3306/connectDB";

	public int addTodo(TodoDto todo) {
		int ret = 1;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
						"insert into todo(title,name,sequence,type,regdate) values(?,?,?,'TODO',?)");) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			ps.setString(4, todo.getRegDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}

		return ret;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select* from todo");) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new TodoDto(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
							rs.getString(6).split(" ")[0]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateTodo(TodoDto param) {
		int ret = 1;
		Long id = param.getId();
		String type = param.getType();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = (Connection) DriverManager.getConnection(url, user, password);
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("update todo set type = ? where id = ?");) {
			ps.setString(1, type);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}
}
