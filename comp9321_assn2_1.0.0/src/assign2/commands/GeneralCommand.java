package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;

public abstract class GeneralCommand implements Command {

	public abstract String doExecute(HttpServletRequest request,
			HttpServletResponse response) throws Comp9321Assign2Exception,
			ServletException, IOException;

	public String handleError(Comp9321Assign2Exception e,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "";
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String res = "";
		try {
			res = doExecute(request, response);
		} catch (Comp9321Assign2Exception e) {
			e.printStackTrace();
			session.setAttribute("errMsg", e.getMessage());
			return "visitorError.jsp";
		}
		return res;
	}

	@Override
	public boolean forward() {
		return false;
	}

}
