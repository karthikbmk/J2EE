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

@WebServlet("/Buy")
public class Buy extends HttpServlet
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		String currentPurchasedItem = request.getParameter("item");
		
		ArrayList<String> boughtItems = (ArrayList<String>) session.getAttribute("boughtItems");

		if (boughtItems == null)
		{
			boughtItems = new ArrayList<String>();
			boughtItems.add(currentPurchasedItem);
		}
		else
		{
			boughtItems.add(currentPurchasedItem);
		}
		session.setAttribute("boughtItems", boughtItems);
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session.getAttribute("itemDetails");

		if (itemDetails == null)
		{
			Items items = new Items();
			session.setAttribute("itemDetails", items.populateItems());
			itemDetails = (ArrayList<HashMap<String, String>>) session.getAttribute("itemDetails");
		}
		HashMap<String, String> itemAndItsOrderCount = (HashMap<String, String>) itemDetails.get(2);

		int orderCount = Integer.parseInt(itemAndItsOrderCount.get(request.getParameter("item")));
		orderCount++;
		itemAndItsOrderCount.put(request.getParameter("item"), Integer.toString(orderCount));

		RequestDispatcher rd = request.getRequestDispatcher("products.html");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

}
