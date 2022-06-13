package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ChatHistory;
import beans.UserInfo;
import dao.ChatHistoryDAO;
import logic.ChatHistorySort;
import logic.SendChatMail;

/**
 * Servlet implementation class SendChat
 */
@WebServlet("/ReturnSendChat")
public class ReturnSendChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");

		HttpSession session = request.getSession();

		//　ログインユーザの情報を取得する		
		UserInfo fromUserInfo = (UserInfo)session.getAttribute("userInfo");
		
		// サークル情報から送信先メールアドレスを取得する		
		UserInfo toUserInfo = (UserInfo)session.getAttribute("counterUserInfo");
		
		try { 
			// メールを送信する
			SendChatMail sendChatMail = new SendChatMail();
			sendChatMail.SendChatMail(subject, toUserInfo.getMailAddress(), contents);
			
			// 現在日時を取得する
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			
			//　チャット内容をDBに登録する
			ChatHistory chatHistory = new ChatHistory(fromUserInfo.getUserId(),toUserInfo.getUserId(),currentDate,fromUserInfo.getMailAddress(),toUserInfo.getMailAddress(),subject,contents,null);
			ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
			chatHistoryDAO.addChatHistory(chatHistory);
			
			// 相手ID、送信日時順にソートしたチャット履歴を取得し、セッションスコープに保存する
			ChatHistorySort chatHistorySort = new ChatHistorySort();
			List<ArrayList<ChatHistory>> chatHistoryList = chatHistorySort.getChatHistory(fromUserInfo);		
			session.setAttribute("chatHistoryList", chatHistoryList);
			
			//　チャット送信完了画面に遷移する		
			response.sendRedirect("chatSendCompleted.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
