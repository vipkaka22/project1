package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Admin;
import assign2.dao.AdminDAO;
import assign2.exception.Comp9321Assign2Exception;

public class AdminProfileCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("adminName");
		System.out.println(name);
		AdminDAO admin;
		try {
			admin = new AdminDAO();
			Admin profile = new Admin();
			profile = admin.getProfile(name);
			session.setAttribute("profile", profile);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "adminMyProfile.jsp";
	}

}
