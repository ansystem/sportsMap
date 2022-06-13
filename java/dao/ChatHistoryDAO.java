package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.ChatHistory;
import common.Common;

public class ChatHistoryDAO {
	
	public int addChatHistory(ChatHistory chatHistory) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "insert into sportsMap.chatHistory values(?,?,?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, chatHistory.getFromUserId()); 
			ps.setString(2, chatHistory.getToUserId());
			ps.setTimestamp(3, chatHistory.getSendDateTime());
			ps.setString(4, chatHistory.getFromAddress());
			ps.setString(5, chatHistory.getToAddress());
			ps.setString(6, chatHistory.getTitle());
			ps.setString(7, chatHistory.getContents());
			int insertCount = ps.executeUpdate();
			
			return insertCount;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	public List<ChatHistory> getChatHistory(String UserId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select * from sportsMap.chatHistory "
					+ "where fromUserId=? or toUserId=? ";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, UserId); 
			ps.setString(2, UserId);
			
			
			ResultSet rs = ps.executeQuery();
			
			List<ChatHistory> chatHistoryList = new ArrayList<ChatHistory>();
			ChatHistory chatHistory = null;
			
			while (rs.next()) {
				String fromUserId = rs.getString("fromUserId");
				String toUserId = rs.getString("toUserId"); 
				Timestamp sendDateTime = rs.getTimestamp("sendDateTime");      
				String fromAddress = rs.getString("fromAddress");        
				String toAddress = rs.getString("toAddress");           
				String title = rs.getString("title"); 
				String contents = rs.getString("contents");
				
				chatHistory = new ChatHistory(fromUserId,toUserId,sendDateTime,fromAddress,toAddress,title,contents,null);
				chatHistoryList.add(chatHistory);
			}
			
			return chatHistoryList;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ChatHistory> getChatHistory(String UserId,String counterId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select * from sportsMap.chatHistory "
					+ "where (fromUserId=? and toUserId=?) "
					+ "or (fromUserId=? and toUserId=?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, UserId); 
			ps.setString(2, counterId);
			ps.setString(3, counterId); 
			ps.setString(4, UserId);
			
		
			
			ResultSet rs = ps.executeQuery();
			
			List<ChatHistory> chatHistoryList = new ArrayList<ChatHistory>();
			ChatHistory chatHistory = null;
			
			while (rs.next()) {
				String fromUserId = rs.getString("fromUserId");
				String toUserId = rs.getString("toUserId"); 
				Timestamp sendDateTime = rs.getTimestamp("sendDateTime");      
				String fromAddress = rs.getString("fromAddress");        
				String toAddress = rs.getString("toAddress");           
				String title = rs.getString("title"); 
				String contents = rs.getString("contents");
				
				chatHistory = new ChatHistory(fromUserId,toUserId,sendDateTime,fromAddress,toAddress,title,contents,null);
				chatHistoryList.add(chatHistory);
			}
			
			return chatHistoryList;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}