package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.*;
import assign2.exception.Comp9321Assign2Exception;
import assign2.util.MailSender;

public class PlaceOrderCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String[] itemIds = (String[]) request.getParameterValues("cartIds");
		String[] quantities = (String[]) request.getParameterValues("quantities");
		if (null == itemIds || itemIds.length == 0) {
			return "shoppingCart.jsp";
		}
		int[] ids = new int[10];
		int[] quans = new int[10];
		for (int i = 0; i < itemIds.length; i++) {
			ids[i] = Integer.parseInt(itemIds[i]);
			quans[i] = Integer.parseInt(quantities[i]);
			System.out.println(ids[i] + ", " + quans[i]);
		}
		
		ShoppingDAO shopping;
		ArrayList<Item> shoppingOrder = new ArrayList<Item>();
		try {
			shopping = new ShoppingDAO();
			for (int i = 0; i < itemIds.length; i++) {
				shopping.placeOrder(username, ids[i], quans[i]);
			}
			shoppingOrder = shopping.showOrder(username);
			if (null == shoppingOrder || shoppingOrder.size() == 0) {
				String e = "Nothing is ordered!";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
			session.setAttribute("shoppingOrder", shoppingOrder);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SellerEmailDAO SellerEmail;
		ArrayList<String> SellerEmails = new ArrayList<String>();;
		try {
			SellerEmail = new SellerEmailDAO();
			for (int i = 0; i < itemIds.length; ++i) {
				String selleremail = SellerEmail.getSellerEmail(ids[i]);
				SellerEmails.add(selleremail);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < SellerEmails.size(); ++i) {
			String subject = "Your Item has been Sold";
			String message = quantities[i] + " of your item: " + shoppingOrder.get(i).getTitle() + " has been bought by "
					+ username + "\n";

			System.out.println(SellerEmails.get(i));
			MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", SellerEmails.get(i), subject, message);
			
		}
		
		return "buyerItemBoughtPage.jsp";
	}

}
