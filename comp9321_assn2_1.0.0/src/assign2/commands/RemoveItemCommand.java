package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.dao.AdminDAO;
import assign2.exception.Comp9321Assign2Exception;

public class RemoveItemCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String itemId = (String) request.getParameter("removeItemId");
		System.out.println(itemId);
		int id = Integer.parseInt(itemId);
		try {
			AdminDAO admin = new AdminDAO();
			String reply = admin.removeItem(id);
			session.setAttribute("errMsg", reply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "adminError";//show the result of item removing, not a real error
	}

}
