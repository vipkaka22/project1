package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;

public class ForwardCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("towelcome"))
			return "welcome.jsp";
		
		else if (action.equals("tobuyermain"))
			return "buyerMainPage.jsp";
		
		else if (action.equals("toadminmain"))
			return "adminMainPage.jsp";
		
		else if (action.equals("tosellermain"))
			return "sellerMainPage.jsp";

		else if (action.equals("tobuyeraccount"))
			return "buyerMyAccount.jsp";
		
		else if (action.equals("toselleraccount"))
			return "sellerMainPage.jsp";
		
		else if (action.equals("tosellanitem"))
			return "sellAnItem.jsp";
		
		else if (action.equals("toadsearch")) 
			return "adSearch.jsp";
		
		else if (action.equals("toregister"))
			return "registPage.jsp";	
		
		else if (action.equals("tologin"))
			return "loginPage.jsp";
		
		else if (action.equals("EditBuyerProfile"))
			return "buyerEditProfile.jsp";
		
		else if (action.equals("EditSellerProfile"))
			return "sellerEditProfile.jsp";
		
		else if (action.equals("registreset"))
			return "registPage.jsp";
		
		else if (action.equals("toadministration"))
			return "adminAdministrationMainPage.jsp";
		
		else if (action.equals("toAdminBuyerListStart"))
			return "adminBuyerListStart.jsp";
		
		else if (action.equals("toAdminSellerListStart"))
			return "adminSellerListStart.jsp";
		
		else if (action.equals("toAdminsearchitem"))
			return "adminSearchPage.jsp";
		
		
		else if (action.equals("itemcancel"))
			return "sellAnItem.jsp";
		
		else if (action.equals("toupdatesellingitem")){
			String updateitemid = request.getParameter("updateitemid");
			System.out.println(updateitemid);
			HttpSession ses = request.getSession();
			ses.setAttribute("pictureURL", null);
			ses.setAttribute("updateitemid", updateitemid);
			return "sellerItemUpdate.jsp";
		}
		
		
		
		//No need to add logout forward
		return null;
	}

}
