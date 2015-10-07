package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;

public class PicturePreviewForCreateCommand extends GeneralCommand {

	public PicturePreviewForCreateCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub
		String pictureURL = request.getParameter("pictureurl");
		HttpSession ses = request.getSession();
		ses.setAttribute("pictureURL", pictureURL);
		System.out.println(pictureURL);
		return "sellAnItem.jsp";

	}

}
