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

public class ShowOrderCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		ShoppingDAO shopping;
		try {
			shopping = new ShoppingDAO();
			ArrayList<Item> shoppingOrder = shopping.showOrder(username);
			if (null == shoppingOrder || shoppingOrder.size() == 0) {
				String e = "Nothing is ordered!";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
			session.setAttribute("shoppingOrder", shoppingOrder);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "buyerItemBoughtPage.jsp";
	}

}
