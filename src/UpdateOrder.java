import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		response.getWriter().println(request.getParameter("ID") + " ::: " + request.getParameter("quantity") + " ::: " +  request.getParameter("price"));;
		Float newTotalPrice = Float.parseFloat(request.getParameter("quantity").toString()) * Float.parseFloat(request.getParameter("price").toString());
		

		MongoClient mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("local");
		

		DBCollection orderDetails_Tbl = db.getCollection("orderDetails");
			
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(request.getParameter("ID")));		
		
		DBCursor cursor = orderDetails_Tbl.find(searchQuery);		
		
		DBObject currentDBObject = cursor.next();
		
		BasicDBObject documentsToBeUpdated = new BasicDBObject();
		documentsToBeUpdated.put("_id", new ObjectId(request.getParameter("ID")));
		
		BasicDBObject updateData = new BasicDBObject();		
		updateData.put("quantity", request.getParameter("quantity"));
		updateData.put("price", newTotalPrice.toString());
		updateData.put("order_id", currentDBObject.get("order_id"));
		updateData.put("userName", currentDBObject.get("userName"));	
		updateData.put("prodName", currentDBObject.get("prodName"));	
		updateData.put("prodDesc", currentDBObject.get("prodDesc"));	
		updateData.put("deliveryDate", currentDBObject.get("deliveryDate"));			
		
		orderDetails_Tbl.updateMulti(documentsToBeUpdated,updateData);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ViewOrders");
		rd.forward(request, response);
		
		mongo.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
