package assign2.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.*;

public class SellerRegisterDAO {
	private Connection connection;
	private Seller seller;
	
	public SellerRegisterDAO(Seller seller) throws Comp9321Assign2Exception {
		connection = ConnectionFactory.Connection();
		this.seller = seller;
	}
	
	public void InsertSeller() throws Exception {
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `seller` "
					+ "(`email`, `username`, `password`, `firstname`, `lastname`, `nickname`, `birth`, `address`, `credit`, `ban`) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
			pstmt.setString(1, seller.getEmail());
			pstmt.setString(2, seller.getUsername());
			pstmt.setString(3, seller.getPassword());
			pstmt.setString(4, seller.getFirstname());
			pstmt.setString(5, seller.getLastname());
			pstmt.setString(6, seller.getNickname());
			pstmt.setInt(7, seller.getBirth());
			pstmt.setString(8, seller.getAddress());
			pstmt.setString(9, seller.getCredit());
			pstmt.setInt(10, seller.getBan());
			
			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in inserting the seller");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
