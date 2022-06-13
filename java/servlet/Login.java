package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChatHistory;
import beans.CircleInfo;
import beans.UserInfo;
import dao.CircleInfoDAO;
import dao.UserInfoDAO;
import logic.ChatHistorySort;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ★全体的にtab遷移を有効にする	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		
		UserInfo userInfo = userInfoDAO.getUserInfo(userId, password);
		
		// ユーザ情報が取得できなかった場合、エラーメッセージを表示する、		
		if (userInfo==null) {
			request.setAttribute("checkExistUser", "NotExist");
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {
			
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
			
			System.out.println("chatHistoryListのサイズ"+chatHistoryList.size());
			
			response.sendRedirect("myPage.jsp");
		}
		
	}

}
