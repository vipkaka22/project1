package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.ManageItemDAO;
import assign2.exception.Comp9321Assign2Exception;

public class CancelPauseItemCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String itemId = (String) request.getParameter("pauseItemId");
		String sellername = (String)session.getAttribute("username");
		int id = Integer.parseInt(itemId);
		ManageItemDAO ManageitemDAO;
		try {
			ManageitemDAO = new ManageItemDAO();
			ManageitemDAO.cancelPauseItem(id);
			ArrayList<Item> sellerItemList = ManageitemDAO.getAllItemsOfSeller(sellername);
			session.setAttribute("selleritemlist", sellerItemList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "sellerManageItem.jsp";
	}

}
