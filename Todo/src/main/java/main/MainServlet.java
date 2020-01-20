package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Todo.TodoDao;
import Todo.TodoDto;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		TodoDao dao = new TodoDao();
		List<TodoDto> list = dao.getTodos();
		request.setAttribute("todo_list", list);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}
}
