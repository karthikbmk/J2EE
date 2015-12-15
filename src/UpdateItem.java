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

@WebServlet("/UpdateItem")
public class UpdateItem extends HttpServlet 
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String itemID = request.getParameter("prdName");
		String price = request.getParameter("price");
		

		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session
				.getAttribute("itemDetails");
		
		itemDetails.get(1).put(itemID, price);
		
		RequestDispatcher rd = request.getRequestDispatcher("StoreManagerServlet");
		rd.forward(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResponse(request, response);
	}

}
