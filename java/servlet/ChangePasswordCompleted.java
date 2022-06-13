package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserInfo;
import common.Common;
import dao.UserInfoDAO;

/**
 * Servlet implementation class userRegistrationCompleted
 */
@WebServlet("/ChangePasswordCompleted")
public class ChangePasswordCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newPassword= request.getParameter("newPassword");

		HttpSession session = request.getSession();
		
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		userInfo.setPassword(newPassword);
		
		//　パスワードを更新する
		UserInfoDAO usreInfoDAO = new UserInfoDAO();
		usreInfoDAO.updateUserInfo(userInfo);
		
		//　パスワード変更完了画面に遷移する	
		response.sendRedirect(Common.QSTUDY_URL + "/passwordChangeCompleted.jsp");
	}

}
