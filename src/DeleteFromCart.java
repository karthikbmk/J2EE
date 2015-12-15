import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteFromCart")
public class DeleteFromCart extends HttpServlet 
{
	protected void processResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
		HttpSession session = request.getSession();
		
		String itemToBeDeletedFromTheCart = request.getParameter("ID");
		int toBeDeletedItemIndex;
		
		ArrayList<String> purchasedItemList = (ArrayList<String>)session.getAttribute("boughtItems");
		toBeDeletedItemIndex = purchasedItemList.indexOf(itemToBeDeletedFromTheCart);
		
		purchasedItemList.remove(toBeDeletedItemIndex);
		session.getAttribute("purchasedItemList");
		
		RequestDispatcher rd = request.getRequestDispatcher("ShoppingCart");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processResponse(request, response);
	}

}
