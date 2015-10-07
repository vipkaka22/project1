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

public class RmSellingItemFromDBCommand extends GeneralCommand {

	public RmSellingItemFromDBCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HttpSession ses = request.getSession();
			String sellername = (String) ses.getAttribute("username");
			String[] idList = request.getParameterValues("rmoptions");
			if (null == idList) {
				return "sellerManageItem.jsp";
			} else {
				ManageItemDAO ManageitemDAO = new ManageItemDAO();
				ManageitemDAO.removeItem(idList);
				ManageItemDAO ManageitemDAO2 = new ManageItemDAO();
				ArrayList<Item> sellerItemList = ManageitemDAO2.getAllItemsOfSeller(sellername);
				ses.setAttribute("selleritemlist", sellerItemList);
				return "sellerManageItem.jsp";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
