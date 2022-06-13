package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserInfo;
import common.Common;

public class UserInfoDAO {
	
	/**
	 * ユーザの追加	
	 * @param userInfo
	 */
	public int addUserInfo(UserInfo userInfo) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 

			String sql = "insert into sportsMap.userinfo values(?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUserId());
			ps.setString(2, userInfo.getUserName());
			ps.setString(3, userInfo.getMailAddress());
			ps.setString(4, userInfo.getPassword());
			ps.setInt(5, userInfo.getCircleID());
			
			int updateCount = ps.executeUpdate();
			
			return updateCount;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ユーザの取得
	 * @param userID
	 * @param password null以外を受け取った場合ユーザIDとあわせてキーにする
	 */
	public UserInfo getUserInfo(String userID, String paramPassword) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select userId,userName,mailAddress,password,circleID from sportsMap.userinfo where userId=? ";
			
			if (paramPassword != null) {
				sql += "and password=?";
			}
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userID);
			
			if (paramPassword != null) {
				ps.setString(2, paramPassword);
			}
			
			ResultSet rs = ps.executeQuery();		
			
			if (rs.next()) {
				String userId=rs.getString("userId");
				String userName=rs.getString("userName");
				String mailAddress=rs.getString("mailAddress");
				String password=rs.getString("password");
				int circleID=rs.getInt("circleID");
				
				UserInfo userInfo = new UserInfo(userId,userName,mailAddress,password,circleID);
				return userInfo;
			} else {
				return null;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UserInfo getUserInfoByCircleID(int paramCircleID) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select userId,userName,mailAddress,password,circleID from sportsMap.userinfo where circleID=? ";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, paramCircleID);
			
			ResultSet rs = ps.executeQuery();		
			
			if (rs.next()) {
				String userId=rs.getString("userId");
				String userName=rs.getString("userName");
				String mailAddress=rs.getString("mailAddress");
				String password=rs.getString("password");
				int circleID=rs.getInt("circleID");
				
				UserInfo userInfo = new UserInfo(userId,userName,mailAddress,password,circleID);
				return userInfo;
			} else {
				return null;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ユーザの更新
	 * @param userInfo
	 */
	public int updateUserInfo(UserInfo userInfo) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "update sportsMap.userinfo set userName=?, mailAddress=?, password=?, circleID=? where userId=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userInfo.getUserName());
			ps.setString(2, userInfo.getMailAddress());
			ps.setString(3, userInfo.getPassword());
			ps.setInt(4, userInfo.getCircleID()); 
			ps.setString(5, userInfo.getUserId()); 
			
			int updateCount = ps.executeUpdate();
			
			return updateCount;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}