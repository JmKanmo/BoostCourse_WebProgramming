package Todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {

	private String getType(String param) {
		if (param.equals("TODO"))
			return "DOING";
		else
			return "DONE";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String type = getType(request.getParameter("type"));
			TodoDao tododao = new TodoDao();
			TodoDto tododto = new TodoDto.Builder().id_(id).type_(type).build();
			response.setHeader("Content-Type", "text/plain");
			PrintWriter writer = response.getWriter();

			if (tododao.updateTodo(tododto) == 1) {
				writer.print("success");
			} else {
				writer.print("fail");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
