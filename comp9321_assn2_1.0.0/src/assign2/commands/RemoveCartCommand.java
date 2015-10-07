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

public class RemoveCartCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String[] itemIds = (String[]) request.getParameterValues("cartIds");
		String[] quantities = (String[]) request.getParameterValues("quantities");
		if (null == itemIds || itemIds.length == 0) {
			return "shoppingCart.jsp";
		}
		int[] ids = new int[10];
		int[] quans = new int[10];
		for (int i = 0; i < itemIds.length; i++) {
			ids[i] = Integer.parseInt(itemIds[i]);
			quans[i] = Integer.parseInt(quantities[i]);
		}
		
		ShoppingDAO shopping;
		try {
			shopping = new ShoppingDAO();
			for (int i = 0; i < itemIds.length; i++) {
				shopping.removeCart(username, ids[i], quans[i]);
			}
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
