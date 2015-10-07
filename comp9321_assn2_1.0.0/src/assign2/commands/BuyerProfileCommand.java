package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.Item;
import assign2.bean.*;
import assign2.dao.*;

import java.util.ArrayList;

public class BuyerProfileCommand extends GeneralCommand {
	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception,
			ServletException, IOException {
		String UserName = (String)request.getSession().getAttribute("username");
		MyProfileDAO dao;
		try {
			dao = new MyProfileDAO();
			Buyer buyer = dao.SearchBuyer(UserName);
			HttpSession session = request.getSession();
			session.setAttribute("buyer", buyer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "buyerMyProfile.jsp";
	}

}
