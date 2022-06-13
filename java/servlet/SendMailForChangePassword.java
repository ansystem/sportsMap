package servlet;

import java.io.IOException;
import java.util.UUID;

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
@WebServlet("/SendMailForChangePassword")
public class SendMailForChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");

		// ユーザIDをキーにユーザ情報を取得する		
		UserInfoDAO UserInfoDAO = new UserInfoDAO();
		UserInfo userInfo = UserInfoDAO.getUserInfo(userId,null);
		
		try {
			//　ユーザが存在する場合、パスワード変更用のメールを送信する		
			if (userInfo!=null) {
				// UUIDを生成して一時ユーザ情報を作成する			
				UUID uuid = UUID.randomUUID();
				
				TmpUserInfo tmpUserInfo = new TmpUserInfo(2,userInfo.getUserId(),userInfo.getUserName(),userInfo.getMailAddress(),userInfo.getPassword(),uuid.toString());
				
				//　一時ユーザ情報をDBに登録する		
				TmpUserInfoDAO tmpUserInfoDAO = new TmpUserInfoDAO();
				tmpUserInfoDAO.addTmpUserInfo(tmpUserInfo);
				
				// パスワード変更用のメールを送信する 
				SendMail sendMail = new SendMail();
				String subject= "パスワードの変更を完了させてください";
				String contents = "<HTML><BODY>下記URLからパスワード変更を完了させてください。<br>http://localhost:8080/sportsMap/ChangePassword?uuid=" + uuid +"</BODY></HTML>";
				sendMail.sendMail(uuid.toString(), userInfo.getMailAddress(),subject,contents);
				
			}

			//　パスワード変更用メールの送信完了画面に遷移する	
			response.sendRedirect(Common.QSTUDY_URL + "/sendMailForChangePasswordCompleted.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
