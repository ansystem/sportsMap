package servlet.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class example
 */
@WebServlet("/example")
public class example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public example() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regist = request.getParameter("regist");
		String Msg = request.getParameter("Msg");
		
		System.out.println(regist+"だよ");
		System.out.println(Msg+"だよ2");
		request.setAttribute("regist", regist);
		
		RequestDispatcher rd = request.getRequestDispatcher("resultExapmle.jsp");
		rd.forward(request, response);
	}

}
