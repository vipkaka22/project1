package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;

public class LogoutCommand extends GeneralCommand {

//	public LogOffCommand() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub
				
		HttpSession session = request.getSession();
		session.setAttribute("buyerName", null);
		session.setAttribute("sellerName", null);
		session.setAttribute("userName", null);
		session.setAttribute("pictureURL", null);
		return "welcome.jsp";

	}

}
