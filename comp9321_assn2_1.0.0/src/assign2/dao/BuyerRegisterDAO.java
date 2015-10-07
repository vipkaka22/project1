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

public class BuyerRegisterDAO {
	private Connection connection;
	private Buyer buyer;
	
	public BuyerRegisterDAO(Buyer buyer) throws Comp9321Assign2Exception {
		connection = ConnectionFactory.Connection();
		this.buyer = buyer;
	}
	
	public void InsertBuyer() throws Exception {
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `buyer` "
					+ "(`email`, `username`, `password`, `firstname`, `lastname`, `nickname`, `birth`, `address`, `credit`, `ban`) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
			pstmt.setString(1, buyer.getEmail());
			pstmt.setString(2, buyer.getUsername());
			pstmt.setString(3, buyer.getPassword());
			pstmt.setString(4, buyer.getFirstname());
			pstmt.setString(5, buyer.getLastname());
			pstmt.setString(6, buyer.getNickname());
			pstmt.setInt(7, buyer.getBirth());
			pstmt.setString(8, buyer.getAddress());
			pstmt.setString(9, buyer.getCredit());
			pstmt.setInt(10, buyer.getBan());
			
			int rst = pstmt.executeUpdate();
			if (rst != 1) {
				throw new Comp9321Assign2Exception("failed in inserting the buyer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
