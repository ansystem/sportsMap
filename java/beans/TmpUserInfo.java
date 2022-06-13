package beans;

import java.io.Serializable;

public class TmpUserInfo implements Serializable{
	private int type;
	private String userId;
	private String userName; 
	private String mailAddress; 
	private String password; 
	private String uuid;
	
	/**
	 * @param type
	 * @param userId
	 * @param userName
	 * @param mailAddress
	 * @param password
	 * @param uuid
	 */
	public TmpUserInfo(int type, String userId, String userName, String mailAddress, String password, String uuid) {
		this.type = type;
		this.userId = userId;
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
		this.uuid = uuid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	} 
	
}