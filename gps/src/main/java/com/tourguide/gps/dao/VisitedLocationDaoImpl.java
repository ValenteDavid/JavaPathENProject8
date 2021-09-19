package com.tourguide.gps.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tourguide.gps.domain.VisitedLocationWithUserName;

import gpsUtil.location.VisitedLocation;

@Repository
public class VisitedLocationDaoImpl implements VisitedLocationDao {

	private List<VisitedLocationWithUserName> visitedLocationsWithUserNameList = new ArrayList<VisitedLocationWithUserName>();

	@Override
	public List<VisitedLocationWithUserName> findByUUID(UUID userId) {
		return visitedLocationsWithUserNameList.stream()
				.filter(visitedLocations -> visitedLocations.userId.equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public VisitedLocationWithUserName save(VisitedLocationWithUserName visitedLocationWithUserName) {
		visitedLocationsWithUserNameList.add(visitedLocationWithUserName);
		return visitedLocationWithUserName;
	}

	@Override
	public VisitedLocationWithUserName findByUUIDOrderByTimeVisitedDesc(UUID userId) {
		List<VisitedLocationWithUserName> visitedLocationByUUID = findByUUID(userId);
		VisitedLocationWithUserName visitedLocationLast = visitedLocationByUUID.get(0);
		
		for (VisitedLocationWithUserName visitedLocationWithUserName : visitedLocationByUUID) {
			if (visitedLocationWithUserName.getTimeVisited().before(visitedLocationLast.getTimeVisited())) {
				visitedLocationLast=visitedLocationWithUserName;
			}
		}
		return visitedLocationLast;
	}
}
