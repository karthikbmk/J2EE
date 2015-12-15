import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddItems")
public class AddItems extends HttpServlet
{	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String itemID = request.getParameter("prdName");
		String price = request.getParameter("prdPrice");
		String prodDesc = request.getParameter("prdDesc");
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session.getAttribute("itemDetails");

		itemDetails.get(0).put(itemID, prodDesc);
		itemDetails.get(1).put(itemID, price);

		session.setAttribute("itemDetails", itemDetails);

		RequestDispatcher rd = request.getRequestDispatcher("StoreManagerServlet");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		processRequest(request, response);
	}

}
