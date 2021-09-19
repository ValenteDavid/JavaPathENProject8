package com.tourguide.gps.controller.dto;

public class NearbyAttractionsDto {
	
	private String userName;
	private double userLatitude;
	private double userLongitude;
	private double attractionLatitude;
	private double attractionLongitude;
	private double distanceUserAttraction;
	private int rewardPoints;
	
	public NearbyAttractionsDto(String userName, double attractionLatitude, double attractionLongitude,
			double userLatitude, double userLongitude, double distanceUserAttraction, int rewardPoints) {
		this.userName = userName;
		this.attractionLatitude = attractionLatitude;
		this.attractionLongitude = attractionLongitude;
		this.userLatitude = userLatitude;
		this.userLongitude = userLongitude;
		this.distanceUserAttraction = distanceUserAttraction;
		this.rewardPoints = rewardPoints;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAttractionLatitude() {
		return attractionLatitude;
	}

	public void setAttractionLatitude(double attractionLatitude) {
		this.attractionLatitude = attractionLatitude;
	}

	public double getAttractionLongitude() {
		return attractionLongitude;
	}

	public void setAttractionLongitude(double attractionLongitude) {
		this.attractionLongitude = attractionLongitude;
	}

	public double getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public double getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(double userLongitude) {
		this.userLongitude = userLongitude;
	}

	public double getDistanceUserAttraction() {
		return distanceUserAttraction;
	}

	public void setDistanceUserAttraction(double distanceUserAttraction) {
		this.distanceUserAttraction = distanceUserAttraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return "NearbyAttractionsdto [userName=" + userName + ", attractionLatitude=" + attractionLatitude
				+ ", attractionLongitude=" + attractionLongitude + ", userLatitude=" + userLatitude + ", userLongitude="
				+ userLongitude + ", distanceUserAttraction=" + distanceUserAttraction + ", rewardPoints="
				+ rewardPoints + "]";
	}

}
