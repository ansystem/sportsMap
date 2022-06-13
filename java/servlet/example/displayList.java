package servlet.example;

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

/**
 * Servlet implementation class displayList
 */
@WebServlet("/displayList")
public class displayList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> displayList = new ArrayList<String>();
		
		//ここでDBから取得する		
		
		displayList.add("姫路城,34.839450,134.693903");		
		displayList.add("大手門（姫路城）,34.83605,134.6931199");	
		displayList.add("菱の門（姫路城）,34.8386654,134.6924566");	
		displayList.add("ずれた現在地,35.75079705711389, 139.78394048465307");
		displayList.add("ずれた現在地2,35.74950838744477, 139.78943364871557");
		
		HttpSession session = request.getSession();
		session.setAttribute("displayList", displayList);
		
		RequestDispatcher rd = request.getRequestDispatcher("example2-2.jsp");
		rd.forward(request, response);
	}

	
}
