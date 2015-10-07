package assign2.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.*;

public class ProfileUpdateDAO {
	private Connection connection;

	public ProfileUpdateDAO() throws Comp9321Assign2Exception {
		connection = ConnectionFactory.Connection();
	}
	/*
	public void UpdateBuyerPassword(String UserName, String NewPassword) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("update buyer set \n"
					+ "password = ? where username = ?;\n");
			pstmt.setString(1, NewPassword);
			pstmt.setString(2, UserName);
			
			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in updating the password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateSellerPassword(String UserName, String NewPassword) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("update seller set \n"
					+ "password = ? where username = ?;\n");
			pstmt.setString(1, NewPassword);
			pstmt.setString(2, UserName);
			
			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in updating the password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public void UpdateBuyer(Buyer buyer) throws Exception {
		try {
			PreparedStatement pstmt = connection.prepareStatement("update buyer set \n"
					+ "password = ?, \n" + "email = ?, \n" + "firstname = ?, \n" + "lastname = ?, \n"
					+ "nickname = ?, \n" + "address = ?, \n" + "credit = ?, \n" + "birth = ? \n"
					+ "where username = ?;\n");
			pstmt.setString(1, buyer.getPassword());
			pstmt.setString(2, buyer.getEmail());
			pstmt.setString(3, buyer.getFirstname());
			pstmt.setString(4, buyer.getLastname());
			pstmt.setString(5, buyer.getNickname());
			pstmt.setString(6, buyer.getAddress());
			pstmt.setString(7, buyer.getCredit());
			pstmt.setInt(8, buyer.getBirth());
			pstmt.setString(9, buyer.getUsername());

			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in updating the buyer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateSeller(Seller seller) throws Exception {
		try {
			PreparedStatement pstmt = connection.prepareStatement("update seller set \n"
					+ "password = ?, \n" + "email = ?, \n" + "firstname = ?, \n" + "lastname = ?, \n"
					+ "nickname = ?, \n" + "address = ?, \n" + "credit = ?, \n" + "birth = ? \n"
					+ "where username = ?;\n");
			pstmt.setString(1, seller.getPassword());
			pstmt.setString(2, seller.getEmail());
			pstmt.setString(3, seller.getFirstname());
			pstmt.setString(4, seller.getLastname());
			pstmt.setString(5, seller.getNickname());
			pstmt.setString(6, seller.getAddress());
			pstmt.setString(7, seller.getCredit());
			pstmt.setInt(8, seller.getBirth());
			pstmt.setString(9, seller.getUsername());

			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in updating the seller");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
