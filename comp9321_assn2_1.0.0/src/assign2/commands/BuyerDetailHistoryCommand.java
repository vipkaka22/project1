package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Buyer;
import assign2.bean.Item;
import assign2.bean.SoldItem;
import assign2.dao.AdminDAO;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;

public class BuyerDetailHistoryCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) request.getParameter("buyerName");
		System.out.println(name);
		AdminDAO detail;
		Buyer buyer = new Buyer();
		ArrayList<SoldItem> history = new ArrayList<SoldItem>();
		try {
			detail = new AdminDAO();
			buyer = detail.findBuyer(name);
			history = detail.getHistory(name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (null != buyer) {
			
			session.setAttribute("buyer", buyer);
			session.setAttribute("history", history);
			return "adminBuyerDetail.jsp";
		} else {
			String res = "Buyer not found!";
			session.setAttribute("errMsg", res);
			return "adminError.jsp";
		}
		
	}

}
