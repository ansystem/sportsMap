package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChatHistory;
import beans.CircleInfo;
import beans.UserInfo;
import dao.ChatHistoryDAO;
import dao.CircleInfoDAO;
import dao.UserInfoDAO;

/**
 * Servlet implementation class ReturnChat
 */
@WebServlet("/ReturnChat")
public class ReturnChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String counterUserId = request.getParameter("counterUserId");
		
		
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
		
		List<ChatHistory> returnChatHistoryList = chatHistoryDAO.getChatHistory(userInfo.getUserId(), counterUserId);
		session.setAttribute("returnChatHistoryList", returnChatHistoryList);

		
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo counterUserInfo = userInfoDAO.getUserInfo(counterUserId, null);
		
		
		session.setAttribute("counterUserInfo", counterUserInfo);
		
		CircleInfoDAO circleInfoDAO = new CircleInfoDAO();
		
		
		CircleInfo circleInfo = circleInfoDAO.getCircleInfo(counterUserInfo.getCircleID());
		
		// 選択したサークル情報をセッションスコープに保存する		
		session.setAttribute("searchCircleInfo", circleInfo);
		
		// チャット返信画面に遷移する
		response.sendRedirect("returnChat.jsp");
	}

	

}
