package assign2.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assign2.bean.Item;
import assign2.dao.ManageItemDAO;
import assign2.dao.SearchDAO;
import assign2.exception.Comp9321Assign2Exception;
import assign2.util.Utils;

public class AddSellingItemToDBCommand extends GeneralCommand {

	public AddSellingItemToDBCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub
 
		HttpSession ses = request.getSession();

		String sellername = (String) ses.getAttribute("username");

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String editor = request.getParameter("editor");
		// int year = Integer.parseInt(request.getParameter("year"));
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		String journal = request.getParameter("journal");
		// int volume = Integer.parseInt(request.getParameter("volume"));
		// int number = Integer.parseInt(request.getParameter("number"));
		String volume = request.getParameter("volume");
		String number = request.getParameter("number");
		String publisher = request.getParameter("publisher");
		String isbn = request.getParameter("isbn");
		String pages = request.getParameter("pages");
		String picture = request.getParameter("picture");
		String booktitle = request.getParameter("booktitle");
		// float price = Float.parseFloat(request.getParameter("price"));
		String price = request.getParameter("price");
		// int pause = Integer.parseInt(request.getParameter("pause"));

		/*************************
		 * 
		 * 
		 * Need to check validity of input etc. ISBN, picture url, price..
		 * 
		 * 
		 **************************/

		// Date addingDate = new Date();

		Item newItem = new Item();

		// newItem.setAddtime(addingDate);

		newItem.setAuthor(author);
		newItem.setBooktitle(booktitle);
		newItem.setEditor(editor);
		newItem.setIsbn(isbn);
		newItem.setJournal(journal);
		//System.out.println("ooooooooooooooooonumber is " + number);
		
		if (title.equals("")) {
			String e = "Sorry title can not be empty!";
			ses.setAttribute("errMsg", e);
			return "sellerError.jsp";
		} else {
			newItem.setTitle(title);
		}
		
		if (year.equals("")){
			
		} else if (!Utils.isBirthYear(year)) {
			String e = "Sorry the input of year is wrong";
			ses.setAttribute("errMsg", e);
			return "sellerError.jsp";
		} else {
			newItem.setYear(Integer.parseInt(year));
		}
		
		if (volume.equals("")){
			
		} else if (!Utils.isPositiveInteger(volume)) {
			String e = "Sorry the input of volume is wrong";
			ses.setAttribute("errMsg", e);
			return "sellerError.jsp";
		} else {
			newItem.setVolume(Integer.parseInt(volume));
		}
		
		
		if (number.equals("")){
			
		} else if (!Utils.isPositiveInteger(number)) {
			String e = "Sorry the input of number is wrong";
			ses.setAttribute("errMsg", e);
			return "sellerError.jsp";
		} else {
			newItem.setNumber(Integer.parseInt(number));
		}
		
		newItem.setPages(pages);
		newItem.setPicture(picture);
		
		if (price.equals("")){
			 
		} else if (!Utils.isPrice(price)) {
			String e = "Sorry the input of price is wrong";
			ses.setAttribute("errMsg", e);
			return "sellerError.jsp";
		} else {
			newItem.setPrice(Float.parseFloat(price));
		}
		
		
		newItem.setPublisher(publisher);
		

		
		newItem.setType(type);
		

		

		
		try {
			ManageItemDAO ManageitemDAO = new ManageItemDAO();
			ManageitemDAO.createNewItem(newItem, sellername);
			ManageItemDAO ManageitemDAO2 = new ManageItemDAO();
			ArrayList<Item> sellerItemList = ManageitemDAO2.getAllItemsOfSeller(sellername);
			ses.setAttribute("selleritemlist", sellerItemList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("xxxxxxxxxxxxxxxxxxx");
		return "sellerManageItem.jsp";
	}

}
