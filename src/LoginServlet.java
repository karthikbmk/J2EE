import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
				
		MongoClient mongo = new MongoClient("localhost", 27017);
		
		
		DB db = mongo.getDB("local");
		
		DBCollection nameID_tbl = db.getCollection("LoginDetails");
		
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", username);
		searchQuery.put("pwd", pwd);
					
		if(request.getParameter("WhoLoggedIn") != null && request.getParameter("WhoLoggedIn").equals("SalesMan"))
		{
			username = request.getParameter("name");
			searchQuery.clear();
			searchQuery.put("username", username);
		}
		
		DBCursor cursor = nameID_tbl.find(searchQuery);		
		
		if (cursor.count() == 0)
		{			
			out.println("<h1 style = \"text-align:center\">You are not a valid User.</h1>");
			out.println("<a href=\"LoginOrSignUp.html\" >Login Again</a>");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("LoggedInUser", username);										
						
			RequestDispatcher rd = request.getRequestDispatcher("products.html");
			rd.forward(request, response);
		}		
				
		mongo.close();
	}

}
