package assign2.dao;

import java.sql.*;
import java.util.ArrayList;

import assign2.exception.Comp9321Assign2Exception;
import assign2.bean.Item;

public class SearchDAO {
	private Connection connection;
	public SearchDAO() throws Exception, SQLException {
		connection = ConnectionFactory.Connection();
	}

	private Item getSearchedItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setTitle(rs.getString("title"));
		item.setAuthor(rs.getString("author"));
		item.setEditor(rs.getString("editor"));
		item.setYear(rs.getInt("year"));
		item.setType(rs.getString("type"));
		item.setJournal(rs.getString("journal"));
		item.setVolume(rs.getInt("volume"));
		item.setNumber(rs.getInt("number"));
		item.setPublisher(rs.getString("publisher"));
		item.setIsbn(rs.getString("isbn"));
		item.setPages(rs.getString("pages"));
		item.setPicture(rs.getString("picture"));
		item.setBooktitle(rs.getString("booktitle"));
		item.setPrice(rs.getFloat("price"));
		item.setPause(rs.getInt("pause"));
		return item;
	}
	
	private String[] split(String keyword) {
		String[] keywords = keyword.split(" ");
		return keywords;
	}
	
	private PreparedStatement prepareSqlStatement(String type, String keyword) throws SQLException {
		String[] keywords = split(keyword);
		
		String sql = "select * from item ";
		boolean firstItem = true;
		for (int i = 0; i < keywords.length; ++i) {
			if (null == keywords[i] || keywords[i].equals(""))
				continue;
			if (firstItem) {
				if (type.equals("all")) {
					sql += "where pause = 0 and (title like ? or author like ? or type like ? or year like ?";
				} else {
					sql += "where pause = 0 and (" + type + " like ?";
				}
				firstItem = false;
			} else {
				if (type.equals("all")) {
					sql += " or title like ? or author like ? or type like ? or year like ?";
				} else {
					sql += " or " + type + " like ?";
				}
			}
		}
		sql += ");";

		System.out.println(sql);
		PreparedStatement stmnt = connection.prepareStatement(sql);
		
		int number = 0;
		for (int i = 0; i < keywords.length; ++i) {
			if (null == keywords[i] || keywords[i].equals(""))
				continue;
			if (type.equals("all")) {
				stmnt.setString(++number, "%" + keywords[i] + "%");
				stmnt.setString(++number, "%" + keywords[i] + "%");
				stmnt.setString(++number, "%" + keywords[i] + "%");
				stmnt.setString(++number, "%" + keywords[i] + "%");
			} else {
				stmnt.setString(++number, "%" + keywords[i] + "%");
			}
		}
		return stmnt;
	}
	
	public ArrayList<Item> search(String type, String keyword)
			throws Comp9321Assign2Exception {
		try {
			PreparedStatement stmnt = prepareSqlStatement(type, keyword);

			ResultSet rs = stmnt.executeQuery();
			ArrayList<Item> list = new ArrayList<Item>();
			while (rs.next()) {
				Item item = getSearchedItem(rs);
				list.add(item);
			}
			connection.close();
			return list;
		} catch (SQLException ne) {
			throw new Comp9321Assign2Exception(ne.getMessage());
		}
	}
	
	public ArrayList<Item> adSearch(String title, String author, String year, String type) throws Comp9321Assign2Exception, SQLException {
		String[] titles = split(title);
		String[] years = split(year);
		String[] authors = split(author);
		String sql = "select * from item where pause = 0 and (type = ?";
		if (!(title == null || title == " ")) {
			for (int j = 0; j < titles.length; j++) {
				if (j == 0) 
					sql += " and title like ?";
				else
					sql += " or title like ?";
			}
		}
		if (!(year == null || year == "")) {
			for (int j = 0; j < years.length; j++) {
				if (j == 0) 
					sql += " and year like ?";
				else
					sql += " or year like ?";
			}
		}
		if (!(author == null || author == "")) {
			for (int j = 0; j < authors.length; j++) {
				if (j == 0) 
					sql += " and author like ?";
				else
					sql += " or author like ?";
			}
		}
		sql += ");";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		int number = 0;
		stmnt.setString(++number, type);
		if (!(title == null || title == " ")) {
			for (int j = 0; j < titles.length; j++) {
				stmnt.setString(++number, "%" + titles[j] + "%");
			}
		}
		if (!(year == null || year == "")) {
			for (int j = 0; j < years.length; j++) {
				stmnt.setString(++number, "%" + years[j] + "%");
			}
		}
		if (!(author == null || author == "")) {
			for (int j = 0; j < authors.length; j++) {
				stmnt.setString(++number, "%" + authors[j] + "%");
			}
		}
		ResultSet rs = stmnt.executeQuery();
		ArrayList<Item> list = new ArrayList<Item>();
		while (rs.next()) {
			Item item = getSearchedItem(rs);
			list.add(item);
		}
		connection.close();
		return list;
	}
	
	public Item findItem(int itemId) throws SQLException {
		String sql = "select * from item where id = ?;";
		PreparedStatement stmnt = connection.prepareStatement(sql);
		stmnt.setString(1, Integer.toString(itemId));
		ResultSet rs = stmnt.executeQuery();
		Item item = new Item();
		rs.next();
		item = getSearchedItem(rs);
		return item;
	}
}
