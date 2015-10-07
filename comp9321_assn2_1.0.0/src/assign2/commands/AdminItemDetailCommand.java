package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.AdminSearchDAO;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;

public class AdminItemDetailCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String itemId = (String) request.getParameter("itemId");
		System.out.println(itemId);
		int id = Integer.parseInt(itemId);
		AdminSearchDAO detail;
		Item item = new Item();
		try {
			detail = new AdminSearchDAO();
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
			return "adminItemDetail.jsp";
		} else {
			String res = "Item not found!";
			session.setAttribute("errMsg", res);
			return "adminError.jsp";
		}
	}

}
