import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/StoreManagerServlet")
public class StoreManagerServlet extends HttpServlet
{

	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		out.println("<a href = \"index.html\">Go to Home</a><br>");
		
		ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session
				.getAttribute("itemDetails");

		if (itemDetails == null)
		{
			Items items = new Items();
			session.setAttribute("itemDetails", items.populateItems());
			itemDetails = (ArrayList<HashMap<String, String>>) session.getAttribute("itemDetails");
		}
		
		HashMap<String,String> itemAndItsDesc = itemDetails.get(0);
		HashMap<String,String> itemAndItsprice = itemDetails.get(1);
		HashMap<String,String> itemAndItsOrderCount = itemDetails.get(2);
		
		String tableHeader;
		tableHeader = "<table border = \"1\" bgcolor=\"orange\">"
				+ "<tr>"
				+ "<th>item Name</th>"
				+ "<th>item Desc</th>"
				+ "<th>Price</th>"
				+ "<th>Update Price</th>"				
				+ "<th>Delete Item ?</th>"				
				+ "</tr>";
		
		StringBuilder tableBody = new StringBuilder();
		
		for (Map.Entry<String, String> entry : itemAndItsDesc.entrySet()) 
		{
			tableBody.append("<tr>")
			.append("<td>")
			.append(entry.getKey())
			.append("</td>")			
			.append("<td>")
			.append(entry.getValue())			
			.append("</td>")			
			.append("<td>")
			.append(itemAndItsprice.get(entry.getKey()) )			
			.append("</td>")		
			.append("<td>")			
			.append("<form action = \"UpdateItem\" method = \"post\"> "
					+ "<input type = \"text\" name = \"price\" value =\"" 
					+ itemAndItsprice.get(entry.getKey()) +"\">"
					+ "<input type = \"hidden\" name = \"prdName\" value =\""
					+ entry.getKey() + "\">"
					+ "<button>Update</Update>" +"</form>")
			
			.append("</td>")			
			.append("<td>")
			.append("<form action = \"deleteItem\" method = \"post\">" + "<input type = \"hidden\" name = \"prdName\" value =\""+ entry.getKey() + "\">" + "<button> Delete </button> </form>")
			.append("</td>")			
			.append("</tr>");

		}
		
		String tableFooter = "</table>";
		out.println("<div align = \"center\">" + "<h1> The Items in the Inventory are: </h1>");
		out.println(tableHeader + tableBody + tableFooter + "</div>");
		
		out.println("<div align = \"center\">" + "<h1> Add an Item to the Inventory here:</h1>");
		
		String dynamicHTML;
		dynamicHTML = "<form action = \"AddItems\" method = \"post\">";
		dynamicHTML += "Item Name :<input type = \"text\" name = \"prdName\"><br>";
		dynamicHTML += "Item Desc :<input type = \"text\" name = \"prdDesc\"><br>";
		dynamicHTML += "Item Price :<input type = \"text\" name = \"prdPrice\"><br>";
		dynamicHTML += "<button> ADD </button><br>";
		dynamicHTML += "</form>";
		
		out.println(dynamicHTML + "</div>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processResponse(request, response);
	}

}
