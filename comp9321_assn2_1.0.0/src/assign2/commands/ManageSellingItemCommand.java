package assign2.commands;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.ManageItemDAO;
import assign2.exception.Comp9321Assign2Exception;

public class ManageSellingItemCommand extends GeneralCommand {

	public ManageSellingItemCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HttpSession ses = request.getSession();
			String sellername = (String)ses.getAttribute("username");
			
			ManageItemDAO ManageitemDAO = new ManageItemDAO();
			ArrayList<Item> sellerItemList = ManageitemDAO.getAllItemsOfSeller(sellername);
			System.out.println("sellerItemlist size is :  " + sellerItemList.size());
			ses.setAttribute("selleritemlist", sellerItemList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "sellerManageItem.jsp";
	}

}
