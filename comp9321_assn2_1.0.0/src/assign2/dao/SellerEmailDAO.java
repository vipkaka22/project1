package assign2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import assign2.bean.Item;
import assign2.util.GetDateTime;

public class SellerEmailDAO {
	private Connection connection;
	public SellerEmailDAO() throws Exception, SQLException {
		// TODO Auto-generated constructor stub
		connection = ConnectionFactory.Connection();
	}
	public String getSellerEmail(int ItemId) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select sellername"
				+ " from sellitem where itemid = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setInt(1, ItemId);
		ResultSet rs = stmnt.executeQuery();
		rs.next();
		String SellerName = rs.getString("sellername");
		sql = "select email"
				+ " from seller where username = ?;";
		stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, SellerName);
		rs = stmnt.executeQuery();
		rs.next();
		String SellerEmail = rs.getString("email");
		return SellerEmail;
	}
}
