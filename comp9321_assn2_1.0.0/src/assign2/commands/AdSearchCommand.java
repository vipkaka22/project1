package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.Item;
import assign2.dao.SearchDAO;

import java.util.ArrayList;

public class AdSearchCommand extends GeneralCommand {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		SearchDAO adSearch;
		try {
			adSearch = new SearchDAO();
			ArrayList<Item> resultList = adSearch.adSearch(title, author, year, type);
			if (null == resultList || resultList.size() == 0) {
				String e = "No result!";
				session.setAttribute("errMsg", e);
				return "buyerError.jsp";
			}
			
			session.setAttribute("resultList", resultList);
			// System.out.println(resultList.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "buyerSearchResultPage.jsp";

	}

	@Override
	public boolean forward() {
		return true;
	}

}
