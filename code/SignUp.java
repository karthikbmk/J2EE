import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet
{
	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");


		MongoClient mongo = new MongoClient("localhost", 27017);


		DB db = mongo.getDB("local");


		DBCollection LoginDetails_tbl = db.getCollection("LoginDetails");

		BasicDBObject doc = new BasicDBObject();
		doc.append("username", uname);
		doc.append("pwd", pwd);				

		LoginDetails_tbl.insert(doc);		
		
		response.getWriter().print("<h1> You have been added as an user. </h1>");
		response.getWriter().print("<a href = \"LoginOrSignUp.html\"> Go Back and Login");
		
		mongo.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processResponse(request, response);
	}

}
