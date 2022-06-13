package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CircleInfo;
import dao.CircleInfoDAO;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sports = request.getParameter("sports");
		String otherSports = request.getParameter("otherSports");
	
		if (sports.equals("その他")) {
			sports = otherSports;
		}
		
		String prefecture = request.getParameter("prefecture");
		String city = request.getParameter("city");
		String[] week = request.getParameterValues("week");
		String sex = request.getParameter("sex");
		String carrier = request.getParameter("carrier");

		CircleInfoDAO circleInfoDAO = new CircleInfoDAO();
		//　cityは検索条件に入れない？
		ArrayList<CircleInfo> circleInfoList = circleInfoDAO.searchCircleInfo(sports, prefecture, null, week, sex, carrier);
		
		// サークルリストをセッションスコープに保存する		
		HttpSession session = request.getSession();
		session.setAttribute("circleInfoList", circleInfoList);
		
		// サークルタイプをセッションスコープに保存する		
		session.setAttribute("circleInfoType", "searchCircle");
		
		session.setAttribute("prefecture", prefecture);
		session.setAttribute("city", city);
		
		// 検索結果画面（マップ）に遷移する
		response.sendRedirect("searchResultMap.jsp");
	}

}
