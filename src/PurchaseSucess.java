import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@WebServlet("/PurchaseSucess")
public class PurchaseSucess extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>)session.getAttribute("itemDetails");
		ArrayList<String> purchasedItemList = (ArrayList<String>)session.getAttribute("boughtItems");
		
		HashMap<String,String> itemAndItsDesc;
		HashMap<String,String> itemAndItsprice;
		HashMap<String,String> itemAndItsOrderCount;
		
		itemAndItsDesc = itemDetails.get(0);
		itemAndItsprice = itemDetails.get(1);
		itemAndItsOrderCount = itemDetails.get(2);
		
		HashSet<String> hs = new HashSet<>();
		hs.addAll(purchasedItemList);
		purchasedItemList.clear();
		purchasedItemList.addAll(hs);
		
		String userName;
		String prodDesc;
		String deliveryDate;
		String price;
		String quantity;

		MongoClient mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("local");
		

		DBCollection orderDetails_Tbl = db.getCollection("orderDetails");	
		BasicDBObject searchQuery = new BasicDBObject();
		
		BasicDBObject doc = new BasicDBObject();
		
		Date today = new Date();
		Date afterFourTeenDays = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 14));
		
		HashMap<String, String> productAndItsConfirmationNumber = new HashMap<String,String>();
				
		for(String item : purchasedItemList)
		{
			
			userName = (String)session.getAttribute("LoggedInUser");

			prodDesc = itemAndItsDesc.get(item);
			deliveryDate = afterFourTeenDays.toString();
			price = Float.toString(((Float.parseFloat(itemAndItsOrderCount.get(item)) * Float.parseFloat(itemAndItsprice.get(item))))); 
			quantity = itemAndItsOrderCount.get(item);
			
			doc.append("prodName", item);
			doc.append("userName", userName);
			doc.append("prodDesc", prodDesc);
			doc.append("deliveryDate", deliveryDate);
			doc.append("price", price);
			doc.append("quantity", quantity);
			
			orderDetails_Tbl.insert(doc);
			doc.clear();					
			
			searchQuery.put("userName", session.getAttribute("LoggedInUser"));
			DBCursor cursor = orderDetails_Tbl.find(searchQuery);
			
			DBObject currentDBObject = cursor.next();
			productAndItsConfirmationNumber.put(item,currentDBObject.get("_id").toString());
			
		}	

		session.invalidate();	
		
		out.println("<h1 align = \"center\">Thanks for Shopping With Us !</h1>");
		out.println("<h2> Please note down your order, confirmation Numbers and their delivery dates</h2>");
		
		String tableHeader;
		StringBuilder tableBody = new StringBuilder();		
		
		tableHeader = "<table border = \"1\">"
				+ "<tr>"
				+ "<th>Ordered Product</th>"
				+ "<th>Confirmation Number</th>"
				+ "<th>Delivery Date</th>"				
				+ "</tr>";
		
		for (Map.Entry<String, String> entry : productAndItsConfirmationNumber.entrySet()) 
		{
		    String key = entry.getKey();
		    String value = entry.getValue();
		    
		    tableBody.append("<tr>")		    
			.append("<td>")
			.append(key)
			.append("</td>")			
			.append("<td>")
			.append(value)
			.append("</td>")			
			.append("<td>")
			.append(afterFourTeenDays.toString())
			.append("</td>")						
			.append("</tr>");		    		   
		    
		}
		out.println(tableHeader + tableBody + "</table></br>");		
		out.println("<a href = \"index.html\">Back to Role Selection Page</a>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		doGet(request, response);
	}

}
