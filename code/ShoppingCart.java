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

@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet
{
	protected void processResponse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		out.println("<h1 align = \"center\"> Welcome Mr. " + session.getAttribute("LoggedInUser"));
		out.println("<h3> Your Shopping Cart is ::");

		ArrayList<String> purchasedItemList = (ArrayList<String>) session.getAttribute("boughtItems");

		if (purchasedItemList == null)
		{
			out.println("<h3>No Items Added to cart !</h3>");
		}
		else
		{

			HashSet<String> hs = new HashSet<>();
			hs.addAll(purchasedItemList);
			purchasedItemList.clear();
			purchasedItemList.addAll(hs);

			String tableHeader;
			tableHeader = "<table border = \"1\">" + "<tr>" + "<th>userName</th>" + "<th>prodName</th>"
					+ "<th>prodDesc</th>" + "<th>Total price</th>" + "<th>Product price</th>" + "<th>quantity</th>"
					+ "<th>Update Quantity</th>" + "<th>Delete Order</th>" + "</tr>";

			StringBuilder tableBody = new StringBuilder();

			ArrayList<HashMap<String, String>> itemDetails = (ArrayList<HashMap<String, String>>) session
					.getAttribute("itemDetails");

			HashMap<String, String> itemAndItsDesc;
			HashMap<String, String> itemAndItsprice;
			HashMap<String, String> itemAndItsOrderCount;

			itemAndItsDesc = itemDetails.get(0);
			itemAndItsprice = itemDetails.get(1);
			itemAndItsOrderCount = itemDetails.get(2);

			for (String item : purchasedItemList)
			{
				tableBody.append("<tr>").append("<td>").append(session.getAttribute("LoggedInUser")).append("</td>")
						.append("<td>").append(item).append("</td>").append("<td>").append(itemAndItsDesc.get(item))
						.append("</td>").append("<td>")
						.append(Float.toString((Float.parseFloat(itemAndItsprice.get(item)) * Float.parseFloat(itemAndItsOrderCount.get(item)))))
						.append("</td>").append("<td>").append(itemAndItsprice.get(item)).append("</td>").append("<td>")
						.append(itemAndItsOrderCount.get(item)).append("</td>").append("<td>")
						.append("<form action=\"UpdateQuantity\" method=\"post\">")
						.append("<input type = \"text\" name = \"quantity\" value =\"" + itemAndItsOrderCount.get(item) + "\">" + "<button> Update </button>")
						.append("<input type =\"hidden\" name = \"productPrice\" value = " + itemAndItsprice.get(item) + ">")
						.append("<input type =\"hidden\" name = \"ID\" value = " + item + ">").append("</form>")
						.append("</td>").append("<td>").append("<form action=\"DeleteFromCart\" method=\"post\">")
						.append("<input type =\"hidden\" name = \"ID\" value = " + item + ">")
						.append("<button>Delete</button>").append("</form>").append("</td>").append("</tr>");
			}

			out.println(tableHeader + tableBody + "</table>");
			out.println("<form action=\"CheckOut\" method=\"post\">");
			out.println("<center><button>" + "Proceed To CheckOut" + "</button></center>");
			out.println("</form>");
			out.println("</br>");
			out.println("<a href = \"products.html\"> Shop Again");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processResponse(request, response);
	}

}
