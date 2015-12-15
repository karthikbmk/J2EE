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


@WebServlet("/UpdateQuantity")
public class UpdateQuantity extends HttpServlet 
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();		
		
		String productId = request.getParameter("ID");
		String quantity = request.getParameter("quantity");
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>)session.getAttribute("itemDetails");
		HashMap<String,String> itemAndItsOrderCount = itemDetails.get(2);
		itemAndItsOrderCount.put(productId, quantity);
		
		itemDetails.set(2, itemAndItsOrderCount);
		
		RequestDispatcher rd = request.getRequestDispatcher("ShoppingCart");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResponse(request, response);
	}

}
