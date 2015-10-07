package assign2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assign2.bean.Item;
import assign2.util.GetDateTime;

public class ShoppingDAO {
	private Connection connection;
	public ShoppingDAO() throws Exception, SQLException {
		connection = ConnectionFactory.Connection();
	}

	private Item getShoppedItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setTitle(rs.getString("title"));
		item.setAuthor(rs.getString("author"));
		item.setYear(rs.getInt("year"));
		item.setType(rs.getString("type"));
		item.setPrice(rs.getFloat("price"));
		item.setCart(rs.getInt("cart"));
		item.setSold(rs.getInt("sold"));
		return item;
	}
	
	private Item getFromSolditem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("itemid"));
		item.setCart(rs.getInt("cart"));
		item.setSold(rs.getInt("sold"));
		return item;
	}
	
	public ArrayList<Item> showCart(String username) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select id, title, author, type, year, price, cart, sold"
				+ " from item, solditem where buyername = ? and id = itemid and cart != 0;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, username);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Item item = getShoppedItem(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}
	
	public ArrayList<Item> showOrder(String username) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select id, title, author, type, year, price, cart, sold"
				+ " from item, solditem where buyername = ? and id = itemid and sold != 0;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, username);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Item item = getShoppedItem(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}
	
	public void addCart(String username, int itemId, int quantity) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select itemid, cart, sold"
				+ " from solditem where buyername = ? and itemid = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, username);
		stmnt.setInt(2, itemId);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Item item = getFromSolditem(rs);
			list.add(item);
		}
		int addQuantity = quantity;
		if (list.size() == 0) {
			String sql1 = "insert into solditem values (?, ?, ?, 0);";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setString(1, username);
			stmnt1.setInt(2, itemId);
			stmnt1.setInt(3, quantity);
			stmnt1.executeUpdate();
		} else {
			quantity += list.get(0).getCart();
			String sql1 = "update solditem set cart = ? where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setInt(1, quantity);
			stmnt1.setString(2, username);
			stmnt1.setInt(3, itemId);
			stmnt1.executeUpdate();
		}
		String nowTime = GetDateTime.getCurTime();
		String sql2 = "insert into userhistory (buyername, itemid, cart, sold, addtime)"
				+ " values (?, ?, ?, '0', ?);";
		PreparedStatement stmnt2 = connection.prepareStatement(sql2);
		stmnt2.setString(1, username);
		stmnt2.setInt(2, itemId);
		stmnt2.setInt(3, addQuantity);
		stmnt2.setString(4, nowTime);
		stmnt2.executeUpdate();
	}
	
	public void removeCart(String username, int itemId, int quantity) throws SQLException {
		String sql = "select itemid, cart, sold"
				+ " from solditem where buyername = ? and itemid = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, username);
		stmnt.setInt(2, itemId);
		ResultSet rs = stmnt.executeQuery();
		rs.next();
		Item item = getFromSolditem(rs);
		int preQuantity = item.getCart();
		int preOrder = item.getSold();
		int temp  = preQuantity - quantity;
		if (temp > 0) {
			String sql1 = "update solditem set cart = ? where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setInt(1, temp);
			stmnt1.setString(2, username);
			stmnt1.setInt(3, itemId);
			stmnt1.executeUpdate();
		} else if (temp == 0 && preOrder > 0){
			String sql1 = "update solditem set cart = ? where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setInt(1, 0);
			stmnt1.setString(2, username);
			stmnt1.setInt(3, itemId);
			stmnt1.executeUpdate();
		} else {
			String sql1 = "delete from solditem where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setString(1, username);
			stmnt1.setInt(2, itemId);
			stmnt1.executeUpdate();
		}
		String nowTime = GetDateTime.getCurTime();
		String sql2 = "insert into userhistory (buyername, itemid, cart, sold, removetime) "
				+ "values (?, ?, ?, '0', ?);";
		PreparedStatement stmnt2 = connection.prepareStatement(sql2);
		stmnt2.setString(1, username);
		stmnt2.setInt(2, itemId);
		if (temp > 0)
			stmnt2.setInt(3, quantity);
		else 
			stmnt2.setInt(3, preQuantity);
		stmnt2.setString(4, nowTime);
		stmnt2.executeUpdate();
	}
	
	public void placeOrder(String username, int itemId, int quantity) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		String sql = "select itemid, cart, sold"
				+ " from solditem where buyername = ? and itemid = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, username);
		stmnt.setInt(2, itemId);
		ResultSet rs = stmnt.executeQuery();
		rs.next();
		Item item = getFromSolditem(rs);
		int preQuantity = item.getCart();
		int preOrder = item.getSold();
		int temp  = preQuantity - quantity;
		if (temp > 0) {
			String sql1 = "update solditem set cart = ?, sold = ? where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setInt(1, temp);
			stmnt1.setInt(2, quantity + preOrder);
			stmnt1.setString(3, username);
			stmnt1.setInt(4, itemId);
			stmnt1.executeUpdate();
		} else {
			String sql1 = "update solditem set cart = ?, sold = ?  where buyername = ? and itemid = ?;";
			PreparedStatement stmnt1 = connection.prepareStatement(sql1);
			stmnt1.setInt(1, 0);
			stmnt1.setInt(2, quantity + preOrder);
			stmnt1.setString(3, username);
			stmnt1.setInt(4, itemId);
			stmnt1.executeUpdate();
		}
		String nowTime = GetDateTime.getCurTime();
		String sql2 = "insert into userhistory (buyername, itemid, cart, sold, soldtime)"
				+ " values (?, ?, ?, ?, ?);";
		PreparedStatement stmnt2 = connection.prepareStatement(sql2);
		stmnt2.setString(1, username);
		stmnt2.setInt(2, itemId);
		if (temp > 0) {
			stmnt2.setInt(3, quantity);
		} else {
			stmnt2.setInt(3, preQuantity);
		}
		stmnt2.setInt(4, quantity);
		stmnt2.setString(5, nowTime);
		stmnt2.executeUpdate();
	}
}
