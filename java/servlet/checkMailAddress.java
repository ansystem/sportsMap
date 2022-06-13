package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TmpUserInfo;
import beans.UserInfo;
import common.Common;
import dao.TmpUserInfoDAO;
import dao.UserInfoDAO;
import logic.SendMail;

/**
 * Servlet implementation class userRegistrationCompleted
 */
@WebServlet("/checkMailAddress")
public class checkMailAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");

		// ユーザIDをキーにユーザ情報を取得する		
		UserInfoDAO UserInfoDAO = new UserInfoDAO();
		UserInfo UserInfo = UserInfoDAO.getUserInfo(userId,null);
		
		try {
			//　ユーザIDが既に存在する場合、新規登録画面に遷移し、エラーメッセージを表示する、		
			if (UserInfo!=null) {
				request.setAttribute("checkExistUser", "duplicateUser");
				RequestDispatcher rd = request.getRequestDispatcher("userRegistration.jsp");
				rd.forward(request, response);
				
			} else {
				String userName= request.getParameter("userName");
				String mailAddress= request.getParameter("mailAddress");
				String password= request.getParameter("password");
				
				// UUIDを生成して一時ユーザ情報を作成する			
				UUID uuid = UUID.randomUUID();
				TmpUserInfo tmpUserInfo = new TmpUserInfo(1,userId,userName,mailAddress,password,uuid.toString());
				
				//　一時ユーザ情報をDBに登録する		
				TmpUserInfoDAO tmpUserInfoDAO = new TmpUserInfoDAO();
				tmpUserInfoDAO.addTmpUserInfo(tmpUserInfo);
				
				// アカウント登録用のメールを送信する
				SendMail sendMail = new SendMail();
				String subject= "アカウント登録を完了させてください";
				String contents = "<HTML><BODY>下記URLからアカウント登録を完了させてください。<br>http://localhost:8080/sportsMap/UserRegistrationCompleted?uuid=" + uuid +"</BODY></HTML>";
				sendMail.sendMail(uuid.toString(), mailAddress,subject,contents);
				
				//　ユーザ情報登録完了画面に遷移する	
				response.sendRedirect(Common.QSTUDY_URL + "/mailSendCompleted.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
