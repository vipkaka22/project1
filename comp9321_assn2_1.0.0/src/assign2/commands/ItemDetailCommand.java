package assign2.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;
import java.io.IOException;
import java.sql.SQLException;

public class ItemDetailCommand extends GeneralCommand {
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Comp9321Assign2Exception {
		HttpSession session = request.getSession();
		String itemId = (String) request.getParameter("itemId");
		System.out.println(itemId);
		int id = Integer.parseInt(itemId);
		SearchDAO detail;
		Item item = new Item();
		try {
			detail = new SearchDAO();
			item = detail.findItem(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (null != item) {
			
			session.setAttribute("selectedItem", item);
			return "itemDetail.jsp";
		} else {
			String res = "Item not found!";
			session.setAttribute("errMsg", res);
			return "buyerError.jsp";
		}

	}
}
