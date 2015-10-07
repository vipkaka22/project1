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

import assign2.bean.*;
import assign2.commands.*;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns ="/admin", displayName = "AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
        map = new HashMap<String, Command>();
        setCommand();
    }
    
    private void setCommand() {
    	map.put("adminlogin", new LoginCommand());
    	map.put("adminProfile", new AdminProfileCommand());
    	map.put("adminlogout", new AdminLogoffCommand());
    	
    	map.put("adminSearch", new AdminSearchCommand());
    	map.put("adminItemDetail", new AdminItemDetailCommand());
    	map.put("removeItem", new RemoveItemCommand());
    	
    	map.put("searchBuyer", new SearchBuyerCommand());
    	map.put("searchSeller", new SearchSellerCommand());
    	
    	map.put("buyerDetailHistory", new BuyerDetailHistoryCommand());
    	map.put("banBuyer", new BanBuyerCommand());
    	map.put("cancelBanBuyer", new CancelBanBuyerCommand());
    	
    	map.put("banSeller", new BanSellerCommand());
    	map.put("cancelBanSeller", new CancelBanSellerCommand());
    	
    	map.put("toadministration", new ForwardCommand());
    	map.put("toAdminsearchitem", new ForwardCommand());
    	map.put("toadminmain", new ForwardCommand());
    	map.put("toAdminBuyerListStart", new ForwardCommand());
    	map.put("toAdminSellerListStart", new ForwardCommand());
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
