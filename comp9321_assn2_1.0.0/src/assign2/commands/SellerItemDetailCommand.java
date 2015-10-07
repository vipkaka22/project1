package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;

public class SellerItemDetailCommand extends GeneralCommand {

	public SellerItemDetailCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String itemId = (String) request.getParameter("sellerSellectedItemId");

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
			
			session.setAttribute("sellerDetailedItemId", item);
			return "sellerManageItemDetail.jsp";
		} else {
			String res = "Item not found!";
			session.setAttribute("errMsg", res);
			return "sellerErrorjsp";
		}
	}

}
