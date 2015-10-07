package assign2.commands;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.dao.*;
import assign2.exception.*;
import assign2.util.*;
import assign2.bean.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterCommand extends GeneralCommand {
	
	@Override
	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception,
			ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("userName").equals("")) {
			String e = "UserName can not be empty!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (request.getParameter("email").equals("")) {
			String e = "Email can not be empty!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (request.getParameter("passWord").equals("")) {
			String e = "Password can not be empty!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (request.getParameter("ConfirmPassWord").equals("")) {
			String e = "Please confirm your password!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (request.getParameter("creditCardNo").equals("")) {
			String e = "credit Card No. can not be empty!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (request.getParameter("passWord").contains(" ")) {
			String e = "Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (!Utils.isEmail(request.getParameter("email"))) {
			String e = "Wrong format of Email!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (!request.getParameter("firstName").equals("") && !Utils.isAlphabetic(request.getParameter("firstName"))) {
			String e = "FirstName can only contain letters";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (!request.getParameter("lastName").equals("") && !Utils.isAlphabetic(request.getParameter("lastName"))) {
			String e = "LastName can only contain letters";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (!Utils.isCreditCardNum(request.getParameter("creditCardNo"))) {
			String e = "Wrong format of Credit Card Number";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		if (!request.getParameter("birthYear").equals("")) {
			if (!Utils.isBirthYear(request.getParameter("birthYear"))) {
				String e = "Wrong format of Year";
				session.setAttribute("errMsg", e);
				return "visitorError.jsp";
			}
		}
		if (!request.getParameter("passWord").equals(request.getParameter("ConfirmPassWord"))) {
			String e = "The confirmed password should be the same as the password";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
			
		if (request.getParameter("identity").equals("BuyerRegist")) {
			Buyer buyer = new Buyer();
			buyer.setEmail((String)request.getParameter("email"));
			buyer.setUsername((String)request.getParameter("userName"));
			buyer.setPassword((String)request.getParameter("passWord"));
			buyer.setFirstname((String)request.getParameter("firstName"));
			buyer.setLastname((String)request.getParameter("lastName"));
			buyer.setNickname((String)request.getParameter("nickName"));
			buyer.setAddress((String)request.getParameter("streetAddress"));
			buyer.setCredit((String)request.getParameter("creditCardNo"));
			if (!request.getParameter("birthYear").equals(""))
				buyer.setBirth(Integer.parseInt((String)request.getParameter("birthYear")));

			LoginDAO checkdao = new LoginDAO();
			if (!checkdao.isBuyerValid(buyer.getUsername())) {
				String e = "The username is already registered!";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
			
			BuyerRegisterDAO dao = new BuyerRegisterDAO(buyer);
			try {
				dao.InsertBuyer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			session.setAttribute("username", buyer.getUsername());
			
			String subject = "Registration Successful";
			String url = "http://localhost:8080/final/buyerMainPage.jsp";
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";
			System.out.println(buyer.getEmail());
			MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", buyer.getEmail(), subject, message);
			
			String e = "Register Successful!\n" + "Please confirm your email!\n";
			session.setAttribute("errMsg", e);
			return "RegisterSuccess.jsp";

		} 
		else {
//		if (request.getParameter("identity").equals("SellerRegist")) {
			Seller seller = new Seller();
			seller.setEmail((String)request.getParameter("email"));
			seller.setUsername((String)request.getParameter("userName"));
			seller.setPassword((String)request.getParameter("passWord"));
			seller.setFirstname((String)request.getParameter("firstName"));
			seller.setLastname((String)request.getParameter("lastName"));
			seller.setNickname((String)request.getParameter("nickName"));
			seller.setAddress((String)request.getParameter("streetAddress"));
			seller.setCredit((String)request.getParameter("creditCardNo"));
			if (!request.getParameter("birthYear").equals(""))
				seller.setBirth(Integer.parseInt((String)request.getParameter("birthYear")));

			LoginDAO checkdao = new LoginDAO();
			if (!checkdao.isSellerValid(seller.getUsername())) {
				String e = "The username is already registered!";
				session.setAttribute("errMsg", e);
				return "sellerError.jsp";
			}
			
			SellerRegisterDAO dao = new SellerRegisterDAO(seller);
			try {
				dao.InsertSeller();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			session.setAttribute("username", seller.getUsername());
			
			String subject = "Registration Successful";
			String url = "http://localhost:8080/final/sellerMainPage.jsp";
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";

			System.out.println(seller.getEmail());
			MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", seller.getEmail(), subject, message);
			
			String e = "Register Successful!\n" + "Please confirm your email!\n";
			session.setAttribute("errMsg", e);
			return "RegisterSuccess.jsp";
		}
	}
	
	public boolean forward() {
		return true;
	}
}
