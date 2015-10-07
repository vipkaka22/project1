package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;

public class VisitorSearchCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String searchType = request.getParameter("searchType");
		String searchContent = null;
		if (searchType.equals("all"))
			searchContent = request.getParameter("searchContent1");
		if (searchType.equals("title"))
			searchContent = request.getParameter("searchContent2");
		if (searchType.equals("author"))
			searchContent = request.getParameter("searchContent3");
		if (searchType.equals("type"))
			searchContent = request.getParameter("searchContent4");
		if (searchType.equals("year")) 
			searchContent = request.getParameter("searchContent5");
		//System.out.println(searchType + "," + searchContent);
		SearchDAO search;
		try {
			search = new SearchDAO();
		
		ArrayList<Item> resultList;
		
		if (null == searchContent || searchContent.equals("")) {
			String e = "Nothing is searched";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		
		resultList = search.search(searchType, searchContent);
		if (null == resultList || resultList.size() == 0) {
			String e = "No result!";
			session.setAttribute("errMsg", e);
			return "visitorError.jsp";
		}
		session.setAttribute("resultList", resultList);
		//System.out.println(resultList.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "searchResultPage.jsp";
	}

}
