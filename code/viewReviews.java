import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@WebServlet("/viewReviews")
public class viewReviews extends HttpServlet 
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String item = request.getParameter("itemId_1");
		PrintWriter out = response.getWriter();
		

		MongoClient mongo = new MongoClient("localhost", 27017);
		

		DB db = mongo.getDB("local");
		
		DBCollection orderDetails_Tbl = db.getCollection("reviews_tbl");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("ProductName", item);		
		
		DBCursor cursor = orderDetails_Tbl.find(searchQuery);			
		
		if (cursor.count() == 0)
		{			
			out.println("<h1 style = \"text-align:center\">No Reviews Found !.</h1>");			
		}
		else
		{
			out.println("<h1 style = \"text-align:center\">Reviews Are :: </h1>");
			String tableHeader;
			tableHeader = "<table border = \"1\" color = \"brown\">"
					+ "<tr>"
					+ "<th>ProductName</th>"
					+ "<th>ProductCategory</th>"
					+ "<th>ProductPrice</th>"
					+ "<th>RetailerName</th>"
					+ "<th>RetailerZip</th>"
					+ "<th>RetailerCity</th>"
					+ "<th>RetailerState</th>"
					+ "<th>ProductOnSale</th>"
					+ "<th>ManufacturerName</th>"
					+ "<th>ManufacturerRebate</th>"
					+ "<th>UserID</th>"
					+ "<th>UserAge</th>"
					+ "<th>UserGender</th>"
					+ "<th>UserOccupation</th>"
					+ "<th>ReviewRating</th>"
					+ "<th>ReviewDate</th>"
					+ "<th>ReviewText</th>"
					+ "</tr>";
			
			StringBuilder tableBody = new StringBuilder();
			
			while(cursor.hasNext())
			{				
				DBObject currentDBObject = cursor.next();				
				tableBody.append("<tr>")
				.append("<td>")
				.append(currentDBObject.get("ProductName"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ProductCategory"))
				.append("</td>")				
				.append("<td>")
				.append(currentDBObject.get("ProductPrice"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("RetailerName"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("RetailerZip"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("RetailerCity"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("RetailerState"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ProductOnSale"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ManufacturerName"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ManufacturerRebate"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("UserID"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("UserAge"))
				.append("<td>")
				.append(currentDBObject.get("UserGender"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("UserOccupation"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ReviewRating"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ReviewDate"))
				.append("</td>")
				.append("<td>")
				.append(currentDBObject.get("ReviewText"))				
				.append("</tr>")
				;
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
