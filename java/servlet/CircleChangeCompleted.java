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

/**
 * Servlet implementation class circleRegistCompleted
 */
@WebServlet("/CircleChangeCompleted")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class CircleChangeCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					counter++;
					
					fileNamesForDB+=fileName;
				}
			}
		}
			
		// ファイルを1つもアップロードしていない場合		
		if (counter==0) {
			System.out.println("aaa"+request.getParameter("imageSrcHidden"));
			fileNamesForDB = request.getParameter("imageSrcHidden");
		}
		
		String sports = request.getParameter("sports");
		
		// 種目がその他の場合、プルダウンの値を取得する	
		if (sports.equals("その他")) {
			String otherSports = request.getParameter("otherSports");
			sports = otherSports;
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

		HttpSession session = request.getSession();
		
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		CircleInfo circleInfo = new CircleInfo(userInfo.getCircleID(),circleName,sports,prefecture,city,detailAddress,lng,lat,"",week,activeDateTime,sex,carrier,position,introduce,scale,genderRatio,0,fileNamesForDB);
		
		CircleInfoDAO circleInfoDAO = new CircleInfoDAO();
		// サークル情報を更新		
		circleInfoDAO.updateCircleInfo(circleInfo);
		
		// サークル情報をセッションスコープに登録		
		session.setAttribute("circleInfo", circleInfo);
		
		response.sendRedirect("circleChangeCompleted.jsp");
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
