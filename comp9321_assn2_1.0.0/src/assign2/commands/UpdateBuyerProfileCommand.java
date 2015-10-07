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
import assign2.util.*;

import java.util.ArrayList;

public class UpdateBuyerProfileCommand extends GeneralCommand {

	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception {
		HttpSession session = request.getSession();
		String UserName = (String)session.getAttribute("username");
		Buyer OrgBuyer = new Buyer();
		MyProfileDAO dao;
		try {
			dao = new MyProfileDAO();
			OrgBuyer = dao.SearchBuyer(UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (request.getParameter("password").contains(" ")) {
			String e = "Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			return "buyerError.jsp";
		}
		if (!request.getParameter("email").equals("") && !Utils.isEmail(request.getParameter("email"))) {
			String e = "Wrong format of Email!";
			session.setAttribute("errMsg", e);
			return "buyerError.jsp";
		}
		if (!request.getParameter("firstName").equals("") && !Utils.isAlphabetic(request.getParameter("firstName"))) {
			String e = "FirstName can only contain letters";
			session.setAttribute("errMsg", e);
			return "buyerError.jsp";
		}
		if (!request.getParameter("lastName").equals("") && !Utils.isAlphabetic(request.getParameter("lastName"))) {
			String e = "LastName can only contain letters";
			session.setAttribute("errMsg", e);
			return "buyerError.jsp";
		}
		if (!request.getParameter("creditCardNo").equals("") && !Utils.isCreditCardNum(request.getParameter("creditCardNo"))) {
			String e = "Wrong format of Credit Card Number";
			session.setAttribute("errMsg", e);
			return "buyerError.jsp";
		}
		if (!request.getParameter("birthYear").equals("")) {
			if (!Utils.isBirthYear(request.getParameter("birthYear"))) {
				String e = "Wrong format of Year of Birth";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
		}
		
		Buyer buyer = OrgBuyer;

		if (!request.getParameter("password").equals(""))
			buyer.setPassword(request.getParameter("password"));
		if (!request.getParameter("email").equals(""))
			buyer.setEmail(request.getParameter("email"));
		if (!request.getParameter("firstName").equals(""))
			buyer.setFirstname(request.getParameter("firstName"));
		if (!request.getParameter("lastName").equals(""))
			buyer.setLastname(request.getParameter("lastName"));
		if (!request.getParameter("nickName").equals(""))
			buyer.setNickname(request.getParameter("nickName"));
		if (!request.getParameter("streetAddress").equals(""))
			buyer.setAddress(request.getParameter("streetAddress"));
		if (!request.getParameter("creditCardNo").equals(""))
			buyer.setCredit(request.getParameter("creditCardNo"));
		if (!request.getParameter("birthYear").equals(""))
			buyer.setBirth(Integer.parseInt((String)request.getParameter("birthYear")));
		
		ProfileUpdateDAO daoUpdate = new ProfileUpdateDAO();
		try {
			daoUpdate.UpdateBuyer(buyer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao = new MyProfileDAO();
			buyer = dao.SearchBuyer(UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("buyer", buyer);
		
		return "buyerMyProfile.jsp";
	}

}
