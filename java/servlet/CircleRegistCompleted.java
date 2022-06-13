package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.CircleInfo;
import beans.UserInfo;
import dao.CircleInfoDAO;
import dao.UserInfoDAO;

/**
 * Servlet implementation class circleRegistCompleted
 */
@WebServlet("/CircleRegistCompleted")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class CircleRegistCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("getServletContext().getRealPath(\"/file\")"+getServletContext().getRealPath("/file"));
		System.out.println("request.getContextPath() "+request.getContextPath() );
		
		String fileName = null;
		String fileNamesForDB = "";
		int counter = 0;
		// アップロードしたファイルを取得する　★本番環境ではパス変える必要があるかも→つまったらgetContextPathを試してみる
		for (Part part : request.getParts()) {
			//登録できるファイルは5つまで			
			if (counter == 5) {
				break;
			}
			if (part.getName().equals("file")) {
				// ★ファイル名はサークルID+連番に変えることでファイル名重複時にアップロードされない問題を解消する？そこまでする？				
				fileName = getFileName(part);
				
				//　ファイル名のカンマを空文字列に置換する
				fileName = fileName.replace(",", "");
				
				if (fileName.length() > 0) {
					part.write(getServletContext().getRealPath("/file") + "/" + fileName);
						
					if (counter!=0) {
						fileNamesForDB+=",";
					}
					
					fileNamesForDB+=fileName;
					counter++;
				}
			}
		}
		
		
		String sportsType = request.getParameter("sports");

		// 種目がその他の場合、プルダウンの値を取得する		
		if (sportsType.equals("その他")) {
			sportsType = request.getParameter("otherSports");
		}
		
		String circleName = request.getParameter("circleName");
		String introduce = request.getParameter("introduce");
		String prefecture = request.getParameter("prefecture");
		String city = request.getParameter("city");
		String detailAddress = request.getParameter("detailAddress");
		String[] weekList = request.getParameterValues("week");
		String activeDateTime = request.getParameter("activeDateTime");
		String scale = request.getParameter("scale");
		String genderRatio = request.getParameter("genderRatio");
		String sex = request.getParameter("sex");
		String carrier = request.getParameter("carrier");
		String position = request.getParameter("position");
		
		String strlat = request.getParameter("lat");
		String strlng = request.getParameter("lng");
		
		Double lat= Double.parseDouble(strlat);
		Double lng = Double.parseDouble(strlng);
		
		String week = "";
		for (int i=0; i<weekList.length; i++) {
			if (i==0) {
				week += weekList[i];
			} else {
				week += "、";
				week += weekList[i];
			}
		}
		
		CircleInfo circleInfo = new CircleInfo(-1,circleName,sportsType,prefecture,city,detailAddress,lng,lat,"",week,activeDateTime,sex,carrier,position,introduce,scale,genderRatio,0,fileNamesForDB);
		
		CircleInfoDAO circleInfoDAO = new CircleInfoDAO();
		
		// サークル情報をDBに登録		
		circleInfoDAO.addCircleInfo(circleInfo);
		
		// 最大のサークルIDを取得
		int maximumCircleID = circleInfoDAO.GetMaximumCircleID();
		
		// サークル情報をセッションスコープに登録		
		HttpSession session = request.getSession();
		session.setAttribute("circleInfo", circleInfo);
		
		//　ユーザ情報にサークル情報を紐づけ
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
	
		userInfo.setCircleID(maximumCircleID);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		
		userInfoDAO.updateUserInfo(userInfo);
		
		response.sendRedirect("circleRegistCompleted.jsp");
	}
	
	private String getFileName(Part part) {
		String[] splitHeader = part.getHeader("Content-Disposition").split(";");
		
		String fileName = null;
		for (String item : splitHeader) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf('"')).replaceAll("\"", "");
			}
		}
		return fileName;
	}

}
