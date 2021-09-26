package com.tourguide.user.controller.dto;

import com.tourguide.user.domain.UserPreferences;

public class UserPreferenceDto {
	private int tripDuration;
	private int numberOfAdults;
	private int numberOfChildren;

	public UserPreferenceDto() {
	}

	public UserPreferenceDto(int tripDuration, int numberOfAdults, int numberOfChildren) {
		this.tripDuration = tripDuration;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
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

	@Override
	public String toString() {
		return "UserPreferenceDto [tripDuration=" + tripDuration + ", numberOfAdults=" + numberOfAdults
				+ ", numberOfChildren=" + numberOfChildren + "]";
	}

	public static UserPreferenceDto convertToDto(UserPreferences userPreference) {
		return new UserPreferenceDto(
				userPreference.getTripDuration(),
				userPreference.getNumberOfAdults(),
				userPreference.getNumberOfChildren());
	}

}
