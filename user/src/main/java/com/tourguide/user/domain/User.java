package com.tourguide.user.domain;

import java.util.Date;
import java.util.UUID;

/**
 * User
 * @author David
 *
 */
public class User {
	/**
	 * user id
	 */
	private final UUID userId;
	/**
	 * user name
	 */
	private final String userName;
	/**
	 * phone number
	 */
	private String phoneNumber;
	/**
	 * email address
	 */
	private String emailAddress;
	/**
	 * lastest location time
	 */
	private Date latestLocationTimestamp;
	
	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public UUID getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
		this.latestLocationTimestamp = latestLocationTimestamp;
	}
	
	public Date getLatestLocationTimestamp() {
		return latestLocationTimestamp;
	}
}
