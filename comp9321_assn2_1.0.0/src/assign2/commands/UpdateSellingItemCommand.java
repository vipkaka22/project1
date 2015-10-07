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

public class UpdateSellingItemCommand extends GeneralCommand {

	public UpdateSellingItemCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws Comp9321Assign2Exception, ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			HttpSession ses = request.getSession();

			// System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
			// System.out.println("updateitemid is : " +
			// request.getParameter("updateitemid"));

			String tmpid = (String) ses.getAttribute("updateitemid");
			System.out.println("updateitemid 2 is : " + tmpid);
			int updateitemid = Integer.parseInt(tmpid);

			String sellername = (String) ses.getAttribute("username");

			// int updateitemid =
			// Integer.parseInt(request.getParameter("updateitemid"));
			SearchDAO searchitem = new SearchDAO();
			Item itemrec = searchitem.findItem(updateitemid);

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
			System.out.println("picture is:     " + picture);
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
			if (!author.equals("")) {
				newItem.setAuthor(author);
			} else {
				newItem.setAuthor(itemrec.getAuthor());
			}
			if (!booktitle.equals("")) {
				newItem.setBooktitle(booktitle);
			} else {
				newItem.setBooktitle(itemrec.getBooktitle());
			}
			if (!editor.equals("")) {
				newItem.setEditor(editor);
			} else {
				newItem.setEditor(itemrec.getEditor());
			}
			if (!isbn.equals("")) {
				newItem.setIsbn(isbn);
			} else {
				newItem.setIsbn(itemrec.getIsbn());
			}
			if (!journal.equals("")) {
				newItem.setJournal(journal);
			} else {
				newItem.setJournal(itemrec.getJournal());
			}
			
			if (!volume.equals("")) {
				if (!Utils.isPositiveInteger(volume)){
					String e = "Sorry the input of volume should be integer";
					ses.setAttribute("errMsg", e);
					return "sellerError.jsp";
				} else {
					newItem.setVolume(Integer.parseInt(volume));
				}
			} else {
				newItem.setVolume(itemrec.getVolume());
			}
			
			
			if (!number.equals("")) {
				if (!Utils.isPositiveInteger(number)){
					String e = "Sorry the input of number should be integer";
					ses.setAttribute("errMsg", e);
					return "sellerError.jsp";
				} else {
					newItem.setNumber(Integer.parseInt(number));
				}
			} else {
				newItem.setNumber(itemrec.getNumber());
			}
			
			if (!pages.equals("")) {
				newItem.setPages(pages);
			} else {
				newItem.setPages(itemrec.getPages());
			}
			if (null != picture) {
				newItem.setPicture(picture);
			} else {
				newItem.setPicture(itemrec.getPicture());
			}
			if (!price.equals("")) {
				if (!Utils.isPrice(price)){
					String e = "Sorry the input of price is wrong";
					ses.setAttribute("errMsg", e);
					return "sellerError.jsp";
				} else {
					newItem.setPrice(Float.parseFloat(price));
				}
			} else {
				newItem.setPrice(itemrec.getPrice());
			}
			if (!publisher.equals("")) {
				newItem.setPublisher(publisher);
			} else {
				newItem.setPublisher(itemrec.getPublisher());
			}
			if (!title.equals("")) {
				newItem.setTitle(title);
			} else {
				newItem.setTitle(itemrec.getTitle());
			}
			if (!type.equals("")) {
				newItem.setType(type);
			} else {
				newItem.setType(itemrec.getType());
			}
			

			
			
			if (!year.equals("")) {
				if (!Utils.isBirthYear(year)) {
					String e = "Sorry the input of year is wrong";
					ses.setAttribute("errMsg", e);
					return "sellerError.jsp";
				} else {
					newItem.setYear(Integer.parseInt(year));
				}
			} else {
				newItem.setYear(itemrec.getYear());
			}

			ManageItemDAO ManageitemDAO = new ManageItemDAO();
			ManageitemDAO.updateSellingItem(newItem, updateitemid);
			;
			ManageItemDAO ManageitemDAO2 = new ManageItemDAO();
			ArrayList<Item> sellerItemList = ManageitemDAO2.getAllItemsOfSeller(sellername);
			ses.setAttribute("selleritemlist", sellerItemList);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// System.out.println("xxxxxxxxxxxxxxxxxxx");
		return "sellerManageItem.jsp";
	}

}
