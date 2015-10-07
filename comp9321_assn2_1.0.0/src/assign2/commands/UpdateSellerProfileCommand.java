package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;
import assign2.util.Utils;
import assign2.bean.*;
import assign2.dao.*;

import java.util.ArrayList;

public class UpdateSellerProfileCommand extends GeneralCommand {

	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception {
		HttpSession session = request.getSession();
		String UserName = (String)session.getAttribute("username");
		Seller OrgSeller = new Seller();;
		MyProfileDAO dao;
		try {
			dao = new MyProfileDAO();
			OrgSeller = dao.SearchSeller(UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (request.getParameter("password").contains(" ")) {
			String e = "Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			return "sellerError.jsp";
		}
		if (!request.getParameter("email").equals("") && !Utils.isEmail(request.getParameter("email"))) {
			String e = "Wrong format of Email!";
			session.setAttribute("errMsg", e);
			return "sellerError.jsp";
		}
		if (!request.getParameter("firstName").equals("") && !Utils.isAlphabetic(request.getParameter("firstName"))) {
			String e = "FirstName can only contain letters";
			session.setAttribute("errMsg", e);
			return "sellerError.jsp";
		}
		if (!request.getParameter("lastName").equals("") && !Utils.isAlphabetic(request.getParameter("lastName"))) {
			String e = "LastName can only contain letters";
			session.setAttribute("errMsg", e);
			return "sellerError.jsp";
		}
		if (!request.getParameter("creditCardNo").equals("") && !Utils.isCreditCardNum(request.getParameter("creditCardNo"))) {
			String e = "Wrong format of Credit Card Number";
			session.setAttribute("errMsg", e);
			return "sellerError.jsp";
		}
		if (!request.getParameter("birthYear").equals("")) {
			if (!Utils.isBirthYear(request.getParameter("birthYear"))) {
				String e = "Wrong format of Year";
				session.setAttribute("errMsg", e);
				return "sellerError.jsp";
			}
		}
		
		Seller seller = OrgSeller;

		if (!request.getParameter("password").equals(""))
			seller.setPassword(request.getParameter("password"));
		if (!request.getParameter("email").equals(""))
			seller.setEmail(request.getParameter("email"));
		if (!request.getParameter("firstName").equals(""))
			seller.setFirstname(request.getParameter("firstName"));
		if (!request.getParameter("lastName").equals(""))
			seller.setLastname(request.getParameter("lastName"));
		if (!request.getParameter("nickName").equals(""))
			seller.setNickname(request.getParameter("nickName"));
		if (!request.getParameter("streetAddress").equals(""))
			seller.setAddress(request.getParameter("streetAddress"));
		if (!request.getParameter("creditCardNo").equals(""))
			seller.setCredit(request.getParameter("creditCardNo"));
		if (!request.getParameter("birthYear").equals(""))
			seller.setBirth(Integer.parseInt((String)request.getParameter("birthYear")));
		
		ProfileUpdateDAO daoUpdate = new ProfileUpdateDAO();
		try {
			daoUpdate.UpdateSeller(seller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao = new MyProfileDAO();
			seller = dao.SearchSeller(UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("seller", seller);
		
		return "sellerMyProfile.jsp";
	}

}
