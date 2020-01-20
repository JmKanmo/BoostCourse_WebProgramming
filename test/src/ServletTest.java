
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/test")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		Formatter form = new Formatter();

		form.format("== 타입 지정==%n");
		form.format("10 진수%d%n", 20);
		form.format("16 진수%x%n", 20);
		form.format("8 진수%o%n", 20);
		form.format("실수 %f%n", 3.5);
		form.format("문자열 %s%n", "Hello");

		form.format("== 인덱스 지정==%n");
		form.format("%1$d %2$s%n", 23, "홍길동");
		form.format("10진수:%1$d 8진수:%1$o 16진수:%1$x%n", 20);

		form.format("== flags 지정==%n");
		form.format("[디폴트 정렬 %10d]%n", 27);
		form.format("[왼쪽 정렬 %-10d]%n", 27);
		form.format("[부호 표시 %+10d]%n", 27);
		form.format("[타입 명시 %#10x]%n", 27);
		form.format("[0 채우기 %010d]%n", 27);
		form.format("[음수는 괄호로 %(10d]%n", -27);
		form.format("[양수는 공백 % d]%n", 27);
		form.format("[양수는 공백 % d]%n", -27);
		System.out.println(form.toString());
		form.flush();
		form.format("flush 테스트%n");
		System.out.println(form.toString());

		out.println("<html>");
		out.println("<head><title>현재시간 페이지</title></head>");
		out.println("<body>");
		out.println(form.toString());
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
