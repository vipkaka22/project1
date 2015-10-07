package assign2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assign2.bean.Admin;
import assign2.bean.Buyer;
import assign2.bean.Item;
import assign2.bean.Seller;
import assign2.bean.SoldItem;
import assign2.exception.Comp9321Assign2Exception;

public class AdminDAO {
	private Connection connection;

	public AdminDAO() throws Exception, SQLException {
		connection = ConnectionFactory.Connection();
	}
	
	private Admin getAdmin(ResultSet rs) throws SQLException {
		Admin admin = new Admin();
		admin.setUsername(rs.getString("username"));
		admin.setPassword(rs.getString("password"));
		admin.setEmail(rs.getString("email"));
		admin.setFirstname(rs.getString("firstname"));
		admin.setLastname(rs.getString("lastname"));
		admin.setAddress(rs.getString("address"));
		return admin;
	}

	private Buyer getBuyer(ResultSet rs) throws SQLException {
		Buyer buyer = new Buyer();
		buyer.setBirth(rs.getInt("birth"));
		buyer.setUsername(rs.getString("username"));
		buyer.setPassword(rs.getString("password"));
		buyer.setEmail(rs.getString("email"));
		buyer.setFirstname(rs.getString("firstname"));
		buyer.setLastname(rs.getString("lastname"));
		buyer.setNickname(rs.getString("nickname"));
		buyer.setAddress(rs.getString("address"));
		buyer.setCredit(rs.getString("credit"));
		buyer.setBan(rs.getInt("ban"));
		return buyer;
	}

	private Seller getSeller(ResultSet rs) throws SQLException {
		Seller seller = new Seller();
		seller.setBirth(rs.getInt("birth"));
		seller.setUsername(rs.getString("username"));
		seller.setPassword(rs.getString("password"));
		seller.setEmail(rs.getString("email"));
		seller.setFirstname(rs.getString("firstname"));
		seller.setLastname(rs.getString("lastname"));
		seller.setNickname(rs.getString("nickname"));
		seller.setAddress(rs.getString("address"));
		seller.setCredit(rs.getString("credit"));
		seller.setBan(rs.getInt("ban"));
		return seller;
	}

	private SoldItem getHistoryItem(ResultSet rs) throws SQLException {
		SoldItem soldItem = new SoldItem();
		soldItem.setItemId(rs.getInt("itemid"));
		soldItem.setBuyername(rs.getString("buyername"));
		soldItem.setSellername(rs.getString("sellername"));
		soldItem.setTitle(rs.getString("title"));
		soldItem.setCart(rs.getInt("cart"));
		soldItem.setSold(rs.getInt("sold"));
		soldItem.setAddtime(rs.getString("addtime"));
		soldItem.setRemovetime(rs.getString("removetime"));
		soldItem.setSoldtime(rs.getString("soldtime"));
		return soldItem;
	}

	private Item getSellerItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setPause(rs.getInt("pause"));
		;
		return item;
	}
	
	public Admin getProfile(String name) throws SQLException {
		String sql = "select * from admin where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		ResultSet rs = stmnt.executeQuery();
		rs.next();
		Admin admin = getAdmin(rs);
		connection.close();
		return admin;
	}

	public String removeItem(int id) throws SQLException {
		String sql1 = "delete from sellitem where itemid = ?;";
		PreparedStatement stmnt1 = connection.prepareStatement(sql1);
		stmnt1.setInt(1, id);
		stmnt1.executeUpdate();
		
		String sql2 = "delete from solditem where itemid = ?;";
		PreparedStatement stmnt2 = connection.prepareStatement(sql2);
		stmnt2.setInt(1, id);
		stmnt2.executeUpdate();
		
		String sql3 = "delete from userhistory where itemid = ?;";
		PreparedStatement stmnt3 = connection.prepareStatement(sql3);
		stmnt3.setInt(1, id);
		stmnt3.executeUpdate();
		
		String sql4 = "delete from item where id = ?;";
		PreparedStatement stmnt4 = connection.prepareStatement(sql4);
		stmnt4.setInt(1, id);
		stmnt4.executeUpdate();

		connection.close();
		return "Remove succeed!";

	}

	public ArrayList<Buyer> searchBuyer(String name) throws SQLException {
		ArrayList<Buyer> list = new ArrayList<Buyer>();
		String sql = "select * from buyer where username like ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, "%" + name + "%");
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Buyer item = getBuyer(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}

	public ArrayList<Seller> searchSeller(String name) throws SQLException {
		ArrayList<Seller> list = new ArrayList<Seller>();
		String sql = "select * from seller where username like ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, "%" + name + "%");
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Seller item = getSeller(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}

	public Buyer findBuyer(String name) throws SQLException {
		String sql = "select * from buyer where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		ResultSet rs = stmnt.executeQuery();
		Buyer buyer = new Buyer();
		rs.next();
		buyer = getBuyer(rs);
		return buyer;
	}

	public Seller findSeller(String name) throws SQLException {
		String sql = "select * from seller where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		ResultSet rs = stmnt.executeQuery();
		Seller seller = new Seller();
		rs.next();
		seller = getSeller(rs);
		connection.close();
		return seller;
	}

	public ArrayList<SoldItem> getHistory(String name) throws SQLException {
		ArrayList<SoldItem> list = new ArrayList<SoldItem>();
		String sql = "select userhistory.itemid, title, sellitem.sellername, buyername, cart, sold, addtime, removetime, soldtime "
				+ "from userhistory, sellitem, item where buyername = ? "
				+ "and userhistory.itemid = sellitem.itemid and userhistory.itemid = item.id;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			SoldItem item = getHistoryItem(rs);
			list.add(item);
		}
		return list;
	}

	public Buyer banBuyer(String name) throws SQLException {
		String sql = "update buyer set ban = 1 where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		stmnt.executeUpdate();
		Buyer buyer = findBuyer(name);
		connection.close();
		return buyer;
	}

	public Buyer cancelBanBuyer(String name) throws SQLException {
		String sql = "update buyer set ban = 0 where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		stmnt.executeUpdate();
		Buyer buyer = findBuyer(name);
		connection.close();
		return buyer;
	}

	public ArrayList<Seller> banSeller(String name) throws SQLException {
		String sql = "update seller set ban = 1 where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		stmnt.executeUpdate();

		ArrayList<Item> list = new ArrayList<Item>();
		String sql1 = "select * from item, sellitem where sellername = ? and id = itemid;";
		PreparedStatement stmnt1 = connection.prepareStatement(sql1);
		stmnt1.setString(1, name);
		ResultSet rs = stmnt1.executeQuery();
		while (rs.next()) {
			Item item = getSellerItem(rs);
			list.add(item);
		}
		for (int i = 0; i < list.size(); i++) {
			String sql2 = "update item set pause = 1 where id = ?;";
			PreparedStatement stmnt2 = connection.prepareStatement(sql2);
			stmnt2.setInt(1, list.get(i).getId());
			stmnt2.executeUpdate();
		}
		ArrayList<Seller> sellerList = searchSeller(name);
		connection.close();
		return sellerList;
	}

	public ArrayList<Seller> cancelBanSeller(String name) throws SQLException {
		String sql = "update seller set ban = 0 where username = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, name);
		stmnt.executeUpdate();

		ArrayList<Item> list = new ArrayList<Item>();
		String sql1 = "select * from item, sellitem where sellername = ? and id = itemid;";
		PreparedStatement stmnt1 = connection.prepareStatement(sql1);
		stmnt1.setString(1, name);
		ResultSet rs = stmnt1.executeQuery();
		while (rs.next()) {
			Item item = getSellerItem(rs);
			list.add(item);
		}
		for (int i = 0; i < list.size(); i++) {
			String sql2 = "update item set pause = 0 where id = ?;";
			PreparedStatement stmnt2 = connection.prepareStatement(sql2);
			stmnt2.setInt(1, list.get(i).getId());
			stmnt2.executeUpdate();
		}
		ArrayList<Seller> sellerList = searchSeller(name);
		connection.close();
		return sellerList;
	}

}
