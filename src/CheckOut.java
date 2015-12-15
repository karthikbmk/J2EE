import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();		
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>)session.getAttribute("itemDetails");
				
		HashMap<String,String> itemAndItsprice;
		HashMap<String,String> itemAndItsOrderCount;
				
		itemAndItsprice = itemDetails.get(1);
		itemAndItsOrderCount = itemDetails.get(2);
				
		ArrayList<String> purchasedItemList = (ArrayList<String>)session.getAttribute("boughtItems");
		
		HashSet<String> hs = new HashSet<>();
		hs.addAll(purchasedItemList);
		purchasedItemList.clear();
		purchasedItemList.addAll(hs);
		
		float totalAmount = 0;
		
		for(String item : purchasedItemList)
		{
			totalAmount += (Float.parseFloat(itemAndItsprice.get(item)) * Float.parseFloat(itemAndItsOrderCount.get(item)));  
		}
		
		out.println("<HTML>");
		out.println("<h2>You will be charged <b>$" + Float.toString(totalAmount) + "</b> for the items You've Purchased.</h2>");
		
		out.println("Enter your details :: </br>");
		out.println("Name :"+"<input type = \"text\"></br>");
		out.println("Card Number :"+"<input type = \"text\"></br>");
		out.println("CVV  Number :"+"<input type = \"text\"></br>");
		out.println("Address :"+"<input type = \"text\"></br>");
		
		out.println("<form action = \"PurchaseSucess\"><button>Submit</button></form>");
		out.println("<a href =\"products.html\">" + "Go back to shopping items </a>");
		out.println("</HTML>");		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		processRequest(request, response);
	}

}
