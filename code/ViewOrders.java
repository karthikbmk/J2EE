import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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


@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet 
{
       
	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();		
		
		ArrayList<OrdersPOJO> orders = new ArrayList<OrdersPOJO>();
				

		MongoClient mongo = new MongoClient("localhost", 27017);
		

		DB db = mongo.getDB("local");
		

		DBCollection orderDetails_Tbl = db.getCollection("orderDetails");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userName", session.getAttribute("LoggedInUser"));		
		
		DBCursor cursor = orderDetails_Tbl.find(searchQuery);			
		
		if (cursor.count() == 0)
		{			
			out.println("<h1 style = \"text-align:center\">No orders Found !.</h1>");			
		}
		else
		{
			String tableHeader;
			tableHeader = "<table border = \"1\">"
					+ "<tr>"
					+ "<th>orderID</th>"
					+ "<th>userName</th>"
					+ "<th>prodName</th>"
					+ "<th>prodDesc</th>"
					+ "<th>deliveryDate</th>"
					+ "<th>Total price</th>"
					+ "<th>Product price</th>"
					+ "<th>quantity</th>"
					+ "<th>Delete Order</th>"
					+ "</tr>";
			
			StringBuilder tableBody = new StringBuilder();
			
			while(cursor.hasNext())
			{				
				DBObject currentDBObject = cursor.next();
				Float productPrice = Float.parseFloat((currentDBObject.get("price").toString())) / Float.parseFloat(currentDBObject.get("quantity").toString());
				tableBody.append("<tr>")
				.append("<td>")
				.append(currentDBObject.get("_id"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("userName"))
				.append("</td>")				
				.append("<td>")
				.append(currentDBObject.get("prodName"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("prodDesc"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("deliveryDate"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("price"))
				.append("</td>")
				.append("<td>")
				.append(productPrice)
				.append("</td>")
				.append("<td>")
				.append("<form action=\"UpdateOrder\" method=\"post\">")
				.append("<input type = \"text\" name = \"quantity\" value =\"" + currentDBObject.get("quantity") +  "\">" + "<button> Update </button>")
				.append("<input type =\"hidden\" name = \"ID\" value = " + currentDBObject.get("_id") +">")
				.append("<input type =\"hidden\" name = \"price\" value = " + productPrice +">")
				.append("</form>")
				.append("</td>")				
				.append("<td>")
				.append("<form action=\"deleteOrder\" method=\"post\">")
				.append("<input type =\"hidden\" name = \"ID\" value = " + currentDBObject.get("_id") +">")
				.append("<button>Delete</button>")
				.append("</form>")
				.append("</td>")
				.append("</tr>");
			}
			String tableFooter = "</table>";
			out.println(tableHeader + tableBody + tableFooter);
			out.println("<a href = \"products.html\"> Back To Products Page");
		}		
				
		mongo.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResponse(request, response);
	}

}
