package Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getCurrentTime() {
		LocalDateTime now = LocalDateTime.now(); // 객체 생성&저장
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(now);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String work_input = request.getParameter("work_input");
		String who_input = request.getParameter("who_input");
		int priority = Integer.parseInt(request.getParameter("priority").substring(0, 1));
		TodoDao tododao = new TodoDao();
		tododao.addTodo(new TodoDto(work_input, who_input, priority, "TODO", getCurrentTime()));
		response.sendRedirect("/mavenweb/MainServlet");
	}
}
