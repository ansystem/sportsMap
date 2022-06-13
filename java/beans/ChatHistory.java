package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatHistory implements Serializable{
	private String fromUserId; 
	private String toUserId; 
	private Timestamp sendDateTime; 
	private String fromAddress; 
	private String toAddress; 
	private String title; 
	private String contents;
	private String counterName; //チャット相手のIDを格納（fromかtoのどちらかに格納されている相手のIDをこの列に集約）
	
	
	/**
	 * @param fromUserId
	 * @param toUserId
	 * @param sendDateTime
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param contents
	 * @param counterName
	 */
	public ChatHistory(String fromUserId, String toUserId, Timestamp sendDateTime, String fromAddress, String toAddress,
			String title, String contents, String counterName) {
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.sendDateTime = sendDateTime;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.title = title;
		this.contents = contents;
		this.counterName = counterName;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public Timestamp getSendDateTime() {
		return sendDateTime;
	}
	public void setSendDateTime(Timestamp sendDateTime) {
		this.sendDateTime = sendDateTime;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	} 
}