package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.TmpUserInfo;
import common.Common;

public class TmpUserInfoDAO {
	
	/**
	 * tmpユーザの追加	
	 * @param TmpUserInfo
	 */
	public int addTmpUserInfo(TmpUserInfo TmpUserInfo) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 

			String sql = "insert into sportsMap.TmpUserInfo values(?,?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, TmpUserInfo.getUuid());
			ps.setInt(2, TmpUserInfo.getType());
			ps.setString(3, TmpUserInfo.getUserId());
			ps.setString(4, TmpUserInfo.getUserName());
			ps.setString(5, TmpUserInfo.getMailAddress());
			ps.setString(6, TmpUserInfo.getPassword());
			
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
	 * tmpユーザの取得
	 * @param userID
	 */
	public TmpUserInfo getTmpUserInfo(String paramUuid) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select * from sportsMap.TmpUserInfo where uuid=? ";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, paramUuid);
			
			ResultSet rs = ps.executeQuery();		
			
			if (rs.next()) {
				int type=rs.getInt("type");
				String userId=rs.getString("userId");
				String userName=rs.getString("userName");
				String mailAddress=rs.getString("mailAddress");
				String password=rs.getString("password");
				String uuid=rs.getString("uuid");
				
				TmpUserInfo TmpUserInfo = new TmpUserInfo(type,userId,userName,mailAddress,password,uuid);
				return TmpUserInfo;
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
}