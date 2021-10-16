package com.tourguide.reward.controller.dto;

import java.util.List;

public class RewardDataDto {
	private List<VisitedLocationWithUserNameDto> userVisitedLocations;
	private List<AttractionDto> attractions;

	public RewardDataDto() {
	}

	public RewardDataDto(List<VisitedLocationWithUserNameDto> userVisitedLocations, List<AttractionDto> attractions) {
		this.userVisitedLocations = userVisitedLocations;
		this.attractions = attractions;
	}

	public List<VisitedLocationWithUserNameDto> getUserVisitedLocations() {
		return userVisitedLocations;
	}

	public void setUserVisitedLocations(List<VisitedLocationWithUserNameDto> userVisitedLocations) {
		this.userVisitedLocations = userVisitedLocations;
	}

	public List<AttractionDto> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<AttractionDto> attractions) {
		this.attractions = attractions;
	}

}
