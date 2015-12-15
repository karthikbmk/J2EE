import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@WebServlet("/storeReview")
public class storeReview extends HttpServlet 
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		PrintWriter out = response.getWriter();
		
		Map<String, String[]> requestMap = request.getParameterMap();	

		MongoClient mongo = new MongoClient("localhost", 27017);
		
		DB db = mongo.getDB("local");
		

		DBCollection reviews_tbl = db.getCollection("reviews_tbl");
		
		BasicDBObject doc = new BasicDBObject();
				
		for (Map.Entry<String, String[]> entry : requestMap.entrySet()) 		
			doc.append(entry.getKey(), entry.getValue()[0].toString());		    
		
		reviews_tbl.insert(doc);
		
		mongo.close();
		
		out.println("<h1> <center> Review Submitted Succesfully <center></h1>");
		out.println("<a href = \"submitReview.html\"> Write Another Review");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResponse(request, response);
	}

}
