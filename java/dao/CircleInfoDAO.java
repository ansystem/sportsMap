package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.CircleInfo;
import common.Common;

public class CircleInfoDAO {
	/**
	 * サークル情報追加
	 */
	public int addCircleInfo(CircleInfo circleInfo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "insert into sportsMap.circleInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, null); //サークルIDはint型の自動連番だがnullを設定する為にsetStringを指定
			ps.setString(2, circleInfo.getCircleName());
			ps.setString(3, circleInfo.getSportsType());
			ps.setString(4, circleInfo.getAddress1());
			ps.setString(5, circleInfo.getAddress2());
			ps.setString(6, circleInfo.getAddress3());
			ps.setDouble(7, circleInfo.getLongitude());
			ps.setDouble(8, circleInfo.getLatitude());
			ps.setString(9, circleInfo.getLocationName());
			ps.setString(10, circleInfo.getWeek());
			ps.setString(11, circleInfo.getActDateTime());
			ps.setString(12, circleInfo.getSex());
			ps.setString(13, circleInfo.getCarrier());
			ps.setString(14, circleInfo.getPosition());
			ps.setString(15, circleInfo.getIntroduce());
			ps.setString(16, circleInfo.getScale());
			ps.setString(17, circleInfo.getGenderRatio());
			ps.setInt(18, circleInfo.getVisitors());
			ps.setString(19, circleInfo.getImageSrc());
			
			int insertCount = ps.executeUpdate();
			
			return insertCount;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	

	/**
	 * サークル情報変更
	 */
	public int updateCircleInfo(CircleInfo circleInfo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			System.out.println("circleInfo.getCircleID()"+circleInfo.getCircleID());
			
			String sql = "update sportsMap.circleInfo "
					+ "set circleName=?,"
					+ " sportsType=?,"
					+ " address1=?,"
					+ " address2=?,"
					+ " address3=?,"
					+ " longitude=?,"
					+ " latitude=?,"
					+ " locationName=?,"
					+ " week=?,"
					+ " actDateTime=?,"
					+ " sex=?,"
					+ " carrier=?,"
					+ " position=?,"
					+ " introduce=?,"
					+ " scale=?,"
					+ " genderRatio=?,"
					+ " visitors=?,"
					+ " imageSrc=? "
					+ "where circleID=?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
	
			ps.setString(1, circleInfo.getCircleName());
			ps.setString(2, circleInfo.getSportsType());
			ps.setString(3, circleInfo.getAddress1());
			ps.setString(4, circleInfo.getAddress2());
			ps.setString(5, circleInfo.getAddress3());
			ps.setDouble(6, circleInfo.getLongitude());
			ps.setDouble(7, circleInfo.getLatitude());
			ps.setString(8, circleInfo.getLocationName());
			ps.setString(9, circleInfo.getWeek());
			ps.setString(10, circleInfo.getActDateTime());
			ps.setString(11, circleInfo.getSex());
			ps.setString(12, circleInfo.getCarrier());
			ps.setString(13, circleInfo.getPosition());
			ps.setString(14, circleInfo.getIntroduce());
			ps.setString(15, circleInfo.getScale());
			ps.setString(16, circleInfo.getGenderRatio());
			ps.setInt(17, circleInfo.getVisitors());
			ps.setString(18, circleInfo.getImageSrc());
			ps.setInt(19, circleInfo.getCircleID());
			
			System.out.println("sql"+sql);
			System.out.println("ps"+ps.toString());
			
			int insertCount = ps.executeUpdate();
			
			return insertCount;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * サークル情報取得
	 * @param paramCircleID
	 */
	public CircleInfo getCircleInfo(int paramCircleID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select circleID,circleName,sportsType,address1,address2,address3,longitude,latitude,locationName,week,actDateTime,sex,carrier,position,introduce,scale,genderRatio,visitors,imageSrc "
					+ "from sportsMap.circleInfo "
					+ "where circleID=?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, paramCircleID);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int circleID= rs.getInt("circleID");
				String circleName= rs.getString("circleName");
				String sportsType= rs.getString("sportsType");
				String address1= rs.getString("address1");
				String address2= rs.getString("address2");
				String address3= rs.getString("address3");
				Double longitude= rs.getDouble("longitude");
				Double latitude= rs.getDouble("latitude");
				String locationName= rs.getString("locationName");
				String week= rs.getString("week");
				String actDateTime= rs.getString("actDateTime");
				String sex= rs.getString("sex");
				String carrier= rs.getString("carrier");
				String position= rs.getString("position");
				String introduce= rs.getString("introduce");
				String scale= rs.getString("scale");
				String genderRatio = rs.getString("genderRatio");
				int visitors= rs.getInt("visitors");
				String imageSrc= rs.getString("imageSrc");
				
				CircleInfo circleInfo = new CircleInfo(circleID,circleName,sportsType,address1,address2,address3,longitude,latitude,locationName,week,actDateTime,sex,carrier,position,introduce,scale,genderRatio,visitors,imageSrc);
			
				return circleInfo;
			} else {
				return null;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 最大
	 * @param paramCircleID
	 */
	public int GetMaximumCircleID() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select circleID "
					+ "from sportsMap.circleInfo "
					+ "order by circleID desc";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int circleID= rs.getInt("circleID");
				return circleID;
			} else {
				return -1;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * サークル情報検索
	 * 
	 * @param sportsType
	 * @param address1
	 * @param address2
	 * @param week
	 * @param sex
	 * @param carrier
	 * @return
	 */
	public ArrayList<CircleInfo> searchCircleInfo(String sportsType,String address1,String address2,String[] week,String sex,String carrier) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASS); 
			
			String sql = "select * from sportsMap.circleInfo where sportsType=? ";
			
			
			if (address1!=null) {
				sql += " AND address1=\"" + address1+ "\""; 
			}
			if (address2!=null) {
				sql += " AND address2=\"" + address2+ "\""; 
			}
			
			if (week==null) {
				// nullの場合は何もしない				
			} else if (week.length==1) {
				sql += " AND week like \"%" + week[0]+ "%\" "; 
			} else if (week.length > 1) {
				for (int i=0; i<week.length; i++) {
					if (i==0) {
						sql += " AND (week like \"%" + week[i]+ "%\" "; 
					} else if (i==week.length-1) {
						sql += " OR week like \"%" + week[i]+ "%\") ";
					} else {
						sql += " OR week like \"%" + week[i]+ "%\" ";
					}
				}
			}
			
			if (sex!=null) {
				sql += " AND sex=\"" + sex+ "\""; 
			}
			if (carrier!=null) {
				sql += " AND carrier=\"" + carrier+ "\""; 
			}

			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, sportsType);
			
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<CircleInfo> circleInfoList = new ArrayList<CircleInfo>();
			CircleInfo circleInfo = null;
			
			while (rs.next()) {
				int tmpcircleID= rs.getInt("circleID");
				String tmpcircleName= rs.getString("circleName");
				String tmpsportsType= rs.getString("sportsType");
				String tmpaddress1= rs.getString("address1");
				String tmpaddress2= rs.getString("address2");
				String tmpaddress3= rs.getString("address3");
				Double tmplongitude= rs.getDouble("longitude");
				Double tmplatitude= rs.getDouble("latitude");
				String tmplocationName= rs.getString("locationName");
				String tmpweek= rs.getString("week");
				String tmpactDateTime= rs.getString("actDateTime");
				String tmpsex= rs.getString("sex");
				String tmpcarrier= rs.getString("carrier");
				String tmpposition= rs.getString("position");
				String tmpintroduce= rs.getString("introduce");
				String tmpscale= rs.getString("scale");
				String tmpgenderRatio = rs.getString("genderRatio");
				int tmpvisitors= rs.getInt("visitors");
				String tmpimageSrc= rs.getString("imageSrc");
				
				circleInfo = new CircleInfo(tmpcircleID,tmpcircleName,tmpsportsType,tmpaddress1,tmpaddress2,tmpaddress3,tmplongitude,tmplatitude,tmplocationName,tmpweek,tmpactDateTime,tmpsex,tmpcarrier,tmpposition,tmpintroduce,tmpscale,tmpgenderRatio,tmpvisitors,tmpimageSrc);
				circleInfoList.add(circleInfo);
			} 
			return circleInfoList;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}