package com.tourguide.gps.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import gpsUtil.location.VisitedLocation;

@Repository
public class VisitedLocationDaoImpl implements VisitedLocationDao {

	private List<VisitedLocation> visitedLocations = new ArrayList<VisitedLocation>();

	@Override
	public List<VisitedLocation> findByUUID(UUID userId) {
		return visitedLocations.stream()
				.filter(visitedLocations -> visitedLocations.userId.equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public VisitedLocation save(VisitedLocation visitedLocation) {
		visitedLocations.add(visitedLocation);
		return visitedLocation;
	}

	@Override
	public VisitedLocation findByUUIDOrderByTimeVisitedDesc(UUID userId) {
		List<VisitedLocation> visitedLocationByUUID = findByUUID(userId);
		VisitedLocation visitedLocationLast = visitedLocationByUUID.get(0);
		
		for (VisitedLocation visitedLocation : visitedLocationByUUID) {
			if (visitedLocation.timeVisited.before(visitedLocationLast.timeVisited)) {
				visitedLocationLast=visitedLocation;
			}
		}
		return visitedLocationLast;
	}
}
