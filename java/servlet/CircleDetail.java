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

/**
 * Servlet implementation class CircleDetail
 */
@WebServlet("/CircleDetail")
public class CircleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CircleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strIndex = request.getParameter("index");
		int index = Integer.parseInt(strIndex) - 1;
		
		HttpSession session = request.getSession();
		ArrayList<CircleInfo> circleInfoList = (ArrayList<CircleInfo>)session.getAttribute("circleInfoList");
		
		
		// 選択したサークル情報をセッションスコープに保存する		
		session.setAttribute("searchCircleInfo", circleInfoList.get(index));
		
		
		// サークル情報タイプ（検索したサークル）をセッションスコープに保存する		
		session.setAttribute("circleInfoType","searchCircle");
		
		// サークル詳細画面に遷移する
		response.sendRedirect("circleDetail.jsp");
		
	}


}
