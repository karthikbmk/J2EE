import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteItem")
public class deleteItem extends HttpServlet
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		HttpSession session = request.getSession();
		
		String itemID = request.getParameter("prdName");

		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session.getAttribute("itemDetails");
		
		itemDetails.get(0).remove(itemID);
		itemDetails.get(1).remove(itemID);
		itemDetails.get(2).remove(itemID);
		
		session.setAttribute("itemDetails", itemDetails);
		
		RequestDispatcher rd = request.getRequestDispatcher("StoreManagerServlet");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processResponse(request, response);
	}

}
