package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.TmpUserInfo;
import beans.UserInfo;
import common.Common;
import dao.TmpUserInfoDAO;
import dao.UserInfoDAO;

/**
 * Servlet implementation class userRegistrationCompleted
 */
@WebServlet("/UserRegistrationCompleted")
public class UserRegistrationCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uuid= request.getParameter("uuid");

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		TmpUserInfoDAO tmpUserInfoDAO = new TmpUserInfoDAO();
		
		// uuidをキーに一時ユーザ情報を取得する		
		TmpUserInfo tmpUserInfo = tmpUserInfoDAO.getTmpUserInfo(uuid);
		
		String userId = tmpUserInfo.getUserId();
		String userName= tmpUserInfo.getUserName();
		String mailAddress= tmpUserInfo.getMailAddress();
		String password= tmpUserInfo.getPassword();
		
		UserInfo userInfo = new UserInfo(userId,userName,mailAddress,password,-1);
		
		//　ユーザ情報をDBに登録する		
		userInfoDAO.addUserInfo(userInfo);
		
		HttpSession session = request.getSession();
		
		// ユーザ情報をセッションスコープに保存する		
		session.setAttribute("userInfo", userInfo);
		
		//　ユーザ情報登録完了画面に遷移する	
		response.sendRedirect(Common.QSTUDY_URL + "/userRegistrationCompleted.jsp");
	}

}
