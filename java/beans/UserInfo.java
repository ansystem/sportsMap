package beans;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private String userId;
	private String userName; 
	private String mailAddress; 
	private String password; 
	private int circleID;
	/**
	 * @param userId
	 * @param userName
	 * @param mailAddress
	 * @param password
	 * @param circleID
	 */
	public UserInfo(String userId, String userName, String mailAddress, String password, int circleID) {
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
		this.circleID = circleID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCircleID() {
		return circleID;
	}
	public void setCircleID(int circleID) {
		this.circleID = circleID;
	}
}