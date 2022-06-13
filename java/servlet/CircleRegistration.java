package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserInfo;

/**
 * Servlet implementation class circleRegistration
 */
@WebServlet("/CircleRegistration")
public class CircleRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		// ログインしていない場合、ログイン画面に遷移する		 
		if (userInfo==null) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("circleRegistration.jsp");
		}
	}



}
