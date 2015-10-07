package assign2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import assign2.bean.*;
import assign2.exception.Comp9321Assign2Exception;

public class LoginDAO {

	private Connection connection;

	public LoginDAO() {
		connection = ConnectionFactory.Connection();
	}

	public Admin adminLogin(String adminUsername, String adminPassword) throws Comp9321Assign2Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from admin where username = ?");
			pstmt.setString(1, adminUsername);
			ResultSet results = pstmt.executeQuery();
			// System.out.println("result -----" + results);
			if (results.next()) {
				String passwordrec = results.getString("password");
				if (adminPassword.equals(passwordrec)) {
					Admin admin = new Admin();
					admin.setAddress(results.getString("address"));
					admin.setEmail(results.getString("email"));
					admin.setFirstname(results.getString("firstname"));
					admin.setLastname(results.getString("lastname"));
					admin.setUsername(results.getString("username"));
					return admin;
				} else {
					connection.close();
					return null;
				}
			}
			connection.close();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public Buyer buyerLogin(String buyerUsername, String buyerPassword) throws Comp9321Assign2Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from buyer where username = ?");
			pstmt.setString(1, buyerUsername);
			ResultSet results = pstmt.executeQuery();
			// System.out.println("result -----" + results);
			if (results.next()) {
				String passwordrec = results.getString("password");
				if (buyerPassword.equals(passwordrec)) {
					Buyer buyer = new Buyer();
					buyer.setAddress(results.getString("address"));
					buyer.setBan(results.getInt("ban"));
					buyer.setBirth(results.getInt("birth"));
					buyer.setCredit(results.getString("credit"));
					buyer.setEmail(results.getString("email"));
					buyer.setFirstname(results.getString("firstname"));
					buyer.setLastname(results.getString("lastname"));
					buyer.setNickname(results.getString("nickname"));
					buyer.setUsername(results.getString("username"));
					return buyer;
				} else {
					connection.close();
					return null;
				}
			}
			connection.close();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public boolean isBuyerValid(String buyerUsername) throws Comp9321Assign2Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from buyer where username = ?");
			pstmt.setString(1, buyerUsername);
			ResultSet results = pstmt.executeQuery();

			if (results.next()) {
				connection.close();
				return false;
			}
			else {
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}
	
	public boolean isSellerValid(String sellerUsername) throws Comp9321Assign2Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from seller where username = ?");
			pstmt.setString(1, sellerUsername);
			ResultSet results = pstmt.executeQuery();

			if (results.next()) {
				connection.close();
				return false;
			}
			else {
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}
	
	public Seller sellerLogin(String sellerUsername, String sellerPassword) throws Comp9321Assign2Exception {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from seller where username = ?");
			pstmt.setString(1, sellerUsername);
			ResultSet results = pstmt.executeQuery();
			// System.out.println("result -----" + results);
			if (results.next()) {
				String passwordrec = results.getString("password");
				if (sellerPassword.equals(passwordrec)) {
					Seller seller = new Seller();
					seller.setAddress(results.getString("address"));
					seller.setBan(results.getInt("ban"));
					seller.setBirth(results.getInt("birth"));
					seller.setCredit(results.getString("credit"));
					seller.setEmail(results.getString("email"));
					seller.setFirstname(results.getString("firstname"));
					seller.setLastname(results.getString("lastname"));
					seller.setNickname(results.getString("nickname"));
					seller.setUsername(results.getString("username"));
					return seller;
				} else {
					connection.close();
					return null;
				}
			}
			connection.close();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
