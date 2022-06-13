package beans;

import java.io.Serializable;

public class CircleInfo implements Serializable{
	private int circleID; 
	private String circleName;
	private String sportsType;
	private String address1;
	/**
	 * @param circleID
	 * @param circleName
	 * @param sportsType
	 * @param address1
	 * @param address2
	 * @param address3
	 * @param longitude
	 * @param latitude
	 * @param locationName
	 * @param week
	 * @param actDateTime
	 * @param sex
	 * @param carrier
	 * @param position
	 * @param introduce
	 * @param scale
	 * @param genderRatio
	 * @param visitors
	 * @param imageSrc
	 */
	public CircleInfo(int circleID, String circleName, String sportsType, String address1, String address2,
			String address3, double longitude, double latitude, String locationName, String week, String actDateTime,
			String sex, String carrier, String position, String introduce, String scale, String genderRatio,
			int visitors, String imageSrc) {
		this.circleID = circleID;
		this.circleName = circleName;
		this.sportsType = sportsType;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationName = locationName;
		this.week = week;
		this.actDateTime = actDateTime;
		this.sex = sex;
		this.carrier = carrier;
		this.position = position;
		this.introduce = introduce;
		this.scale = scale;
		this.genderRatio = genderRatio;
		this.visitors = visitors;
		this.imageSrc = imageSrc;
	}
	private String address2; 
	private String address3;
	private double longitude; 
	private double latitude; 
	private String locationName; 
	private String week; 
	private String actDateTime; 
	private String sex;
	private String carrier; 
	private String position; 
	private String introduce; 
	private String scale; 
	private String genderRatio; 
	private int visitors; 
	private String imageSrc; 
	public int getCircleID() {
		return circleID;
	}
	public void setCircleID(int circleID) {
		this.circleID = circleID;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getSportsType() {
		return sportsType;
	}
	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getActDateTime() {
		return actDateTime;
	}
	public void setActDateTime(String actDateTime) {
		this.actDateTime = actDateTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getGenderRatio() {
		return genderRatio;
	}
	public void setGenderRatio(String genderRatio) {
		this.genderRatio = genderRatio;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}