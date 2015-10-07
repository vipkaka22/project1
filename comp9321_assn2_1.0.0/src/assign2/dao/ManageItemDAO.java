package assign2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import assign2.bean.Item;
import assign2.exception.Comp9321Assign2Exception;

public class ManageItemDAO {

	private Connection connection;

	public ManageItemDAO() {
		// TODO Auto-generated constructor stub
		connection = ConnectionFactory.Connection();
	}

	public void createNewItem(Item newItem, String sellername) throws SQLException, Comp9321Assign2Exception {
			// pstmt1 for inserting item table, pstmt2 inserting solditem table
			PreparedStatement pstmt1 = connection.prepareStatement(
					"INSERT INTO `Item` "
							+ "(`title`, `author`, `editor`, `year`, `type`, `journal`, `volume`, `number`, `publisher`, `isbn`, `pages`, `picture`, `booktitle`, `price`, `pause`) "
							+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n",
					Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1, newItem.getTitle());
			pstmt1.setString(2, newItem.getAuthor());
			pstmt1.setString(3, newItem.getEditor());
			pstmt1.setInt(4, newItem.getYear());
			pstmt1.setString(5, newItem.getType());
			pstmt1.setString(6, newItem.getJournal());
			pstmt1.setInt(7, newItem.getVolume());
			pstmt1.setInt(8, newItem.getNumber());
			pstmt1.setString(9, newItem.getPublisher());
			pstmt1.setString(10, newItem.getIsbn());
			pstmt1.setString(11, newItem.getPages());
			pstmt1.setString(12, newItem.getPicture());
			pstmt1.setString(13, newItem.getBooktitle());
			pstmt1.setFloat(14, newItem.getPrice());
			// pause value of a new item should be 0 while it is being created
			pstmt1.setInt(15, 0);
			int rst1 = pstmt1.executeUpdate();

			PreparedStatement pstmt2 = connection.prepareStatement(
					"INSERT INTO `sellitem` " + "(`sellername`, `itemid`) " + "VALUES " + "(?, ?) \n");
			ResultSet rs = pstmt1.getGeneratedKeys();
			if (rs.next()) {
				int id = Integer.parseInt(rs.getObject(1).toString());
				System.out.println(id);
				pstmt2.setString(1, sellername);
				pstmt2.setInt(2, id);

			}
			int rst2 = pstmt2.executeUpdate();
			connection.close();
			if (rst1 != 1 || rst2 != 1) {
				throw new Comp9321Assign2Exception("failed in creating new item");
			}
	}
	
	public void updateSellingItem(Item newItem, int id) throws SQLException, Comp9321Assign2Exception{
		// pstmt1 for inserting item table, pstmt2 inserting solditem table
		PreparedStatement pstmt1 = connection.prepareStatement("update item set title = ?, author = ?, editor = ?, year = ?, type = ?, journal = ?, volume = ?, number = ?, publisher = ?, isbn = ?, pages = ?, picture = ?, booktitle = ?, price = ?, pause = ? where id = ?;");
		pstmt1.setString(1, newItem.getTitle());
		pstmt1.setString(2, newItem.getAuthor());
		pstmt1.setString(3, newItem.getEditor());
		pstmt1.setInt(4, newItem.getYear());
		pstmt1.setString(5, newItem.getType());
		pstmt1.setString(6, newItem.getJournal());
		pstmt1.setInt(7, newItem.getVolume());
		pstmt1.setInt(8, newItem.getNumber());
		pstmt1.setString(9, newItem.getPublisher());
		pstmt1.setString(10, newItem.getIsbn());
		pstmt1.setString(11, newItem.getPages());
		pstmt1.setString(12, newItem.getPicture());
		pstmt1.setString(13, newItem.getBooktitle());
		pstmt1.setFloat(14, newItem.getPrice());
		// pause value of a new item should be 0 while it is being created
		pstmt1.setInt(15, 0);
		pstmt1.setInt(16, id);
		int rst1 = pstmt1.executeUpdate();

//		PreparedStatement pstmt2 = connection.prepareStatement(
//				"update sellitem set sellername = ?, itemid = ? where buyername = ? and itemid = ?;");
//		ResultSet rs = pstmt1.getGeneratedKeys();
//		if (rs.next()) {
//			int id = Integer.parseInt(rs.getObject(1).toString());
//			System.out.println(id);
//			pstmt2.setString(1, sellername);
//			pstmt2.setInt(2, id);
//
//		}
//		int rst2 = pstmt2.executeUpdate();
		connection.close();
		if (rst1 != 1) {
			throw new Comp9321Assign2Exception("failed in creating new item");
		}
	}

	public ArrayList<Item> getAllItemsOfSeller(String sellername) throws Exception {
			PreparedStatement pstmt = connection.prepareStatement("select itemid from sellitem where sellername = ?");
			pstmt.setString(1, sellername);
			ResultSet results = pstmt.executeQuery();
			ArrayList<String> idList = new ArrayList<String>();
			while (results.next()) {
				String id = results.getString("itemid");
				idList.add(id);
			}
			SearchDAO searchSellerItem = new SearchDAO();
			ArrayList<Item> sellerItemList = new ArrayList<Item>();
			for (int i = 0; i < idList.size(); i++) {
				int id = Integer.parseInt(idList.get(i));
				Item sellerItem = searchSellerItem.findItem(id);
				sellerItemList.add(sellerItem);
			}
			return sellerItemList;

	}
	
	public String removeItem(String[] idList) throws SQLException{

		String sql1 = "delete from sellitem where itemid = ?;";
		PreparedStatement stmnt1 = connection.prepareStatement(sql1);
		String sql2 = "delete from solditem where itemid = ?;";
		PreparedStatement stmnt2 = connection.prepareStatement(sql2);
		String sql3 = "delete from userhistory where itemid = ?;";
		PreparedStatement stmnt3 = connection.prepareStatement(sql3);		
		String sql4 = "delete from item where id = ?;";
		PreparedStatement stmnt4 = connection.prepareStatement(sql4);
		for (int i = 0; i < idList.length; i++){

			stmnt1.setInt(1, Integer.parseInt(idList[i]));
			stmnt1.executeUpdate();
			
			stmnt2.setInt(1, Integer.parseInt(idList[i]));
			stmnt2.executeUpdate();
			
			stmnt3.setInt(1, Integer.parseInt(idList[i]));
			stmnt3.executeUpdate();
			
			stmnt4.setInt(1, Integer.parseInt(idList[i]));
			stmnt4.executeUpdate();
		}
		connection.close();
		return "Remove succeed!";
	}
	
	public void pauseItem(int id) throws SQLException {
		String sql = "update item set pause = 1 where id = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setInt(1, id);
		stmnt.executeUpdate();
	}
	
	public void cancelPauseItem(int id) throws SQLException {
		String sql = "update item set pause = 0 where id = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setInt(1, id);
		stmnt.executeUpdate();
	}

}
