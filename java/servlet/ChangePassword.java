package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChatHistory;
import beans.CircleInfo;
import beans.TmpUserInfo;
import beans.UserInfo;
import common.Common;
import dao.CircleInfoDAO;
import dao.TmpUserInfoDAO;
import dao.UserInfoDAO;
import logic.ChatHistorySort;

/**
 * Servlet implementation class userRegistrationCompleted
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uuid= request.getParameter("uuid");

		// uuidをキーに一時ユーザ情報を取得する	
		TmpUserInfoDAO tmpUserInfoDAO = new TmpUserInfoDAO();	
		TmpUserInfo tmpUserInfo = tmpUserInfoDAO.getTmpUserInfo(uuid);
		
		//　ユーザ情報を取得する
		UserInfoDAO usreInfoDAO = new UserInfoDAO();
		UserInfo userInfo = usreInfoDAO.getUserInfo(tmpUserInfo.getUserId(), null);
		
		// ユーザ情報をセッションスコープに保存する			
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		
		// サークル情報を取得してセッションスコープに保存する			
		CircleInfoDAO circleInfoDAO = new CircleInfoDAO();
		CircleInfo circleInfo = circleInfoDAO.getCircleInfo(userInfo.getCircleID());
		session.setAttribute("circleInfo", circleInfo);
		
		// 相手ID、送信日時順にソートしたチャット履歴を取得する
		ChatHistorySort chatHistorySort = new ChatHistorySort();
		List<ArrayList<ChatHistory>> chatHistoryList = chatHistorySort.getChatHistory(userInfo);		
		session.setAttribute("chatHistoryList", chatHistoryList);
				
		if (tmpUserInfo != null) {
			//　パスワード変更画面に遷移する	
			response.sendRedirect(Common.QSTUDY_URL + "/passwordChange.jsp");
		}
	}

}
