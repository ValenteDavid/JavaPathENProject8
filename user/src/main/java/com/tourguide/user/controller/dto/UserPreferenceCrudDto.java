package com.tourguide.user.controller.dto;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.tourguide.user.domain.UserPreferences;

public class UserPreferenceCrudDto {
	
	@NotNull
	private UUID userId;
	@NotNull
	private String userName;
	@NotNull
	@Min(value = 0)
	private int tripDuration;
	@NotNull
	@Min(value = 0)
	private int numberOfAdults;
	@NotNull
	@Min(value = 0)
	private int numberOfChildren;
	
	public UserPreferenceCrudDto() {
	}
	
	public UserPreferenceCrudDto(UUID userId, String userName, int tripDuration, int numberOfAdults,
			int numberOfChildren) {
		this.userId = userId;
		this.userName = userName;
		this.tripDuration = tripDuration;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}
	
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTripDuration() {
		return tripDuration;
	}
	public void setTripDuration(int tripDuration) {
		this.tripDuration = tripDuration;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public static UserPreferences convertToDomain(UserPreferenceCrudDto userPreferenceCrudDto, UserPreferences userPreferences) {
		userPreferences.setTripDuration(userPreferenceCrudDto.getTripDuration());
		userPreferences.setNumberOfAdults(userPreferenceCrudDto.getNumberOfAdults());
		userPreferences.setNumberOfChildren(userPreferenceCrudDto.getNumberOfChildren());
		return userPreferences;
	}
	
}
