import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.bson.types.ObjectId;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@WebServlet("/deleteOrder")
public class deleteOrder extends HttpServlet 
{	
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				
		MongoClient mongo = MongoConnection.getConnection("localhost", 27017);			
					
		DB db = mongo.getDB("local");
				
		DBCollection orderDetails_Tbl = db.getCollection("orderDetails");
		
		BasicDBObject documentToBeDeleted = new BasicDBObject();
		documentToBeDeleted.put("_id", new ObjectId(request.getParameter("ID")));
		
		orderDetails_Tbl.remove(documentToBeDeleted);		
		
		RequestDispatcher rd = request.getRequestDispatcher("/ViewOrders");
		rd.forward(request, response);
			
		mongo.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		processRequest(request, response);
	}

}
