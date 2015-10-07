package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.ShoppingDAO;
import assign2.exception.Comp9321Assign2Exception;

public class AddCartCommand extends GeneralCommand {
	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String itemId = (String) request.getParameter("cartId");
		String quantity = (String) request.getParameter("quantity");
		System.out.println(username);
		System.out.println(itemId);
		System.out.println(quantity);
		int id = Integer.parseInt(itemId);
		int quan = Integer.parseInt(quantity);
		ShoppingDAO shopping;
		try {
			shopping = new ShoppingDAO();
			shopping.addCart(username, id, quan);
			ArrayList<Item> shoppingCart = shopping.showCart(username);
			if (null == shoppingCart || shoppingCart.size() == 0) {
				String e = "Nothing in shopping cart!";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
			request.setAttribute("number", -1);
			session.setAttribute("shoppingCart", shoppingCart);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "shoppingCart.jsp";
	}
}
