package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.*;
import assign2.dao.LoginDAO;
import assign2.exception.Comp9321Assign2Exception;

public class LoginCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception,
			ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		
/*		
		if (null != ses.getAttribute("adminName")) {
			String e = "This admin ID is being used";
			request.setAttribute("errMsg", e);
			return "error";
		}
		if (null != ses.getAttribute("buyerName")) {
			String e = "This buyer ID is being used";
			request.setAttribute("errMsg", e);
			return "error";
		}
		if (null != ses.getAttribute("sellerName")) {
			String e = "This seller ID is being used";
			request.setAttribute("errMsg", e);
			return "error";
		}
*/		
		
		if (null != request.getParameter("adminname")) {
			String adminUsername = request.getParameter("adminname");
			String adminPassword = request.getParameter("adminpassword");
			LoginDAO loginDao = new LoginDAO();
			Admin admin = loginDao.adminLogin(adminUsername, adminPassword);
			if (null != admin){
				
				session.setAttribute("username", admin.getUsername());
				session.setAttribute("adminName", admin.getUsername());
				System.out.println("name: " + admin.getFirstname() +" " + admin.getLastname());
				return "adminMainPage.jsp";
				
			} else {
				
				System.out.println("login failed");
				String e = "Login Failed";
				session.setAttribute("errMsg", e);
				return "adminError.jsp";
			}
		}
		
		String loginType = request.getParameter("loginType");
		//To seek if buyer info is stored in db
		if (loginType.equals("Buyerlogin")){
			String buyerUsername = request.getParameter("username");
			String buyerPassword = request.getParameter("password");
			LoginDAO loginDao = new LoginDAO();
			Buyer buyer = loginDao.buyerLogin(buyerUsername, buyerPassword);
			if (null != buyer && buyer.getBan() == 0){
				
				session.setAttribute("username", buyer.getUsername());
				session.setAttribute("buyerName", buyer.getUsername());
				System.out.println("name: " + buyer.getFirstname() +" " + buyer.getLastname());
				return "buyerMainPage.jsp";
			} else if (null != buyer && buyer.getBan() == 1){
				String e = "Login Failed!\n"
						+ "This user is banned, please contact user assistance";
				session.setAttribute("errMsg", e);
				return "visitorError.jsp";
				
			} else {
				
				System.out.println("login failed");
				String e = "Login Failed!\n"
						+ "User not found!";
				session.setAttribute("errMsg", e);
				return "visitorError.jsp";
			}
		} 
		//To seek if seller info is stored in db
		else if (loginType.equals("Sellerlogin")){
			String sellerUsername = request.getParameter("username");
			String sellerPassword = request.getParameter("password");
			LoginDAO loginDao = new LoginDAO();
			Seller seller = loginDao.sellerLogin(sellerUsername, sellerPassword);
			if (null != seller && seller.getBan() == 0){
				session.setAttribute("username", seller.getUsername());
				session.setAttribute("sellerName", seller.getUsername());
				System.out.println("name: " + seller.getUsername());
				return "sellerMainPage.jsp";
			} else if (null != seller && seller.getBan() == 1){
				String e = "Login Failed!\n"
						+ "This user is banned, please contact user assistance";
				session.setAttribute("errMsg", e);
				
				return "visitorError.jsp";
				
				
			} else {
				
				System.out.println("login failed");
				String e = "Login Failed!\n"
						+ "User not found!";
				session.setAttribute("errMsg", e);
				return "visitorError.jsp";
			}
		}
		
		
		
		
		return null;
		

		
	
		//return "buyerMainPage.jsp";
	}

}
