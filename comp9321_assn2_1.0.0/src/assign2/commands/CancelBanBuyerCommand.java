package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Buyer;
import assign2.dao.AdminDAO;
import assign2.exception.Comp9321Assign2Exception;

public class CancelBanBuyerCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		String name = (String) request.getParameter("buyerName");
		System.out.println(name);
		AdminDAO ban;
		try {
			ban = new AdminDAO();
			Buyer buyer = ban.cancelBanBuyer(name);
			HttpSession session = request.getSession();
			session.setAttribute("buyer", buyer);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "adminBuyerDetail.jsp";

	}

}
