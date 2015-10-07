package assign2.dao;

import java.sql.*;
import java.util.ArrayList;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.*;

public class MyProfileDAO {
	private Connection connection;
	
	public MyProfileDAO() throws Exception, SQLException {
		connection = ConnectionFactory.Connection();
	}
	
	private Buyer getSearchedBuyer(ResultSet rs) throws SQLException {
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
		return buyer;
	}
	
	private Seller getSearchedSeller(ResultSet rs) throws SQLException {
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
		return seller;
	}
	
	public Buyer SearchBuyer(String username)
			throws Comp9321Assign2Exception {
		try {
			PreparedStatement stmnt = connection.prepareStatement(
					"select * from buyer where username = " + "'" + username + "'"+ ";");
			ResultSet rs = stmnt.executeQuery();
			rs.next();
			Buyer buyer = getSearchedBuyer(rs);
/*
			ArrayList<Buyer> list = new ArrayList<Buyer>();
			while (rs.next()) {
				Buyer buyer = getSearchedBuyer(rs);
				list.add(buyer);
			}
*/
			connection.close();
			return buyer;
		} catch (SQLException ne) {
			throw new Comp9321Assign2Exception(ne.getMessage());
		}
	}
	
	public Seller SearchSeller(String username)
			throws Comp9321Assign2Exception {
		try {
			PreparedStatement stmnt = connection.prepareStatement(
					"select * from seller where username = " + "'" + username + "'" + ";");
			ResultSet rs = stmnt.executeQuery();
			rs.next();
			Seller seller = getSearchedSeller(rs);
/*
			ArrayList<Seller> list = new ArrayList<Seller>();
			while (rs.next()) {
				Seller seller = getSearchedSeller(rs);
				list.add(seller);
			}
*/
			connection.close();
			return seller;
		} catch (SQLException ne) {
			throw new Comp9321Assign2Exception(ne.getMessage());
		}
	}
}
