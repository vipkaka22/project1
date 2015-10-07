package assign2.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.commands.*;


@WebServlet(urlPatterns ="/user", displayName = "UserServlet") 
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map;
	
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
        map = new HashMap<String, Command>();
        setCommand();
        
    }

	private void setCommand() {
		map.put("search", new SearchCommand());
		map.put("visitorSearch", new VisitorSearchCommand());
		map.put("itemDetail", new ItemDetailCommand());
		map.put("visitorItemDetail", new VisitorItemDetailCommand());
		map.put("adSearch", new AdSearchCommand());
		map.put("registsubmit", new RegisterCommand());
		map.put("login", new LoginCommand());
		map.put("logout", new LogoutCommand());
		map.put("SellerProfile", new SellerProfileCommand());
		map.put("BuyerProfile", new BuyerProfileCommand());
		map.put("addCart", new AddCartCommand());
		map.put("removeCart", new RemoveCartCommand());
		map.put("placeOrder", new PlaceOrderCommand());
		map.put("itemsubmit", new AddSellingItemToDBCommand());
		map.put("BuyerProfileComplete", new UpdateBuyerProfileCommand());
		map.put("SellerProfileComplete", new UpdateSellerProfileCommand());
		
		map.put("ManageSellingItem", new ManageSellingItemCommand());
		map.put("removesellingitem", new RmSellingItemFromDBCommand());
		map.put("updatesellingitem", new UpdateSellingItemCommand());		
		map.put("sellerItemDetail", new SellerItemDetailCommand());
		map.put("picturepreviewforupdate", new PicturePreviewForUpdateCommand());
		map.put("picturepreviewforcreate", new PicturePreviewForCreateCommand());
		
		map.put("sellerManageItem", new ManageSellingItemCommand());
		map.put("registreset", new ForwardCommand());
		map.put("EditSellerProfile", new ForwardCommand());
		map.put("EditBuyerProfile", new ForwardCommand());
		map.put("towelcome", new ForwardCommand());
		map.put("tobuyermain", new ForwardCommand());
		map.put("tosellermain", new ForwardCommand());
		map.put("tobuyeraccount", new ForwardCommand());
		map.put("toselleraccount", new ForwardCommand());
		map.put("toadsearch", new ForwardCommand());
		map.put("toregister", new ForwardCommand());
		map.put("tologin", new ForwardCommand());
		map.put("tosellanitem", new ForwardCommand());
		map.put("itemcancel", new ForwardCommand());
		map.put("toupdatesellingitem", new ForwardCommand());
		
		map.put("pause", new PauseItemCommand());
		map.put("unpause", new CancelPauseItemCommand());
		
		map.put("tocart", new ShowCartCommand());
		map.put("toOrder", new ShowOrderCommand());
	}
	
	 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Command cmd = map.get(action);
		System.out.println(action);
		/*
		String[] SplitPW;
		if (action.contains("updatepw ")) {
			System.out.println(action);
			SplitPW = action.split(" ");
			cmd = new UpdateBuyerPasswordCommand();
		}
		else {
			cmd = map.get(action);
			System.out.println(action);
		}
		*/
		
		if (null == cmd) {
			cmd = map.get("error");
		}
		String nextPage = cmd.execute(request, response);
		System.out.println(nextPage);
		
		while (nextPage.indexOf('.') < 0) {
			cmd = map.get(nextPage);
			System.out.println(nextPage);
			nextPage = cmd.execute(request, response);
		}
		
		if (cmd.forward()) {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/" + nextPage);
			rd.forward(request, response);
		} else {
			response.sendRedirect(nextPage);
		}
	}
}
