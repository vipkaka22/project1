package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.*;
import assign2.dao.*;

import java.util.ArrayList;

public class UpdateBuyerPasswordCommand extends GeneralCommand {

	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception {
		HttpSession session = request.getSession();
		String UserName = (String)session.getAttribute("username");
		String action = request.getParameter("action");
		String[] SplitPW = action.split(" ");
		String NewPassword = SplitPW[1];
		
		ProfileUpdateDAO daoUpdate = new ProfileUpdateDAO();
		try {
//			daoUpdate.UpdateBuyerPassword(UserName, NewPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyProfileDAO dao;
		Buyer buyer = new Buyer();
		try {
			dao = new MyProfileDAO();
			buyer = dao.SearchBuyer(UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("buyer", buyer);
		return "buyerEditProfile.jsp";
	}

}
