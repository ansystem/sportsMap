package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserInfo;
import dao.UserInfoDAO;

/**
 * Servlet implementation class UserChangeCompleted
 */
@WebServlet("/UserChangeCompleted")
public class UserChangeCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String mailAddress = request.getParameter("mailAddress");
		String passwordChange = request.getParameter("passwordChange");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String userId = request.getParameter("userId");
		
		String pw = null;
		if ("on".equals(passwordChange)) {
			pw = newPassword;
		} else {
			pw = oldPassword;
		}
		
		UserInfo userInfo = new UserInfo(userId,userName,mailAddress,pw,-1);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		
		// ユーザ情報を更新する		
		userInfoDAO.updateUserInfo(userInfo);
		
		HttpSession session = request.getSession();
		UserInfo sessionUserInfo = (UserInfo)session.getAttribute("userInfo");
		
		// 変更後ユーザ情報をセッションスコープに保存する		
		sessionUserInfo.setUserName(userName);
		sessionUserInfo.setMailAddress(mailAddress);
		sessionUserInfo.setPassword(newPassword);
		
		// ユーザ情報変更完了画面に遷移する
		response.sendRedirect("userChangeCompleted.jsp");
	}

}
