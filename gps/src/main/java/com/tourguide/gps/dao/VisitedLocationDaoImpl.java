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
	public void setVisitedLocationsWithUserNameList(List<VisitedLocationWithUserName> visitedLocationsWithUserNameList) {
		this.visitedLocationsWithUserNameList = visitedLocationsWithUserNameList;
	}

	@Override
	public List<VisitedLocationWithUserName> getVisitedLocationsWithUserNameList() {
		return visitedLocationsWithUserNameList;
	}

	@Override
	public List<VisitedLocationWithUserName> findByUUID(UUID userId) {
		return visitedLocationsWithUserNameList.stream()
				.filter(visitedLocations -> visitedLocations.userId.equals(userId))
				.collect(Collectors.toList());
	}
	
	@Override
	public synchronized List<VisitedLocationWithUserName> findByUserName(String userName) {
		return visitedLocationsWithUserNameList.stream()
				.filter(visitedLocations -> visitedLocations.getUserName().equals(userName))
				.collect(Collectors.toList());
	}
	
	@Override
	public synchronized VisitedLocationWithUserName save(VisitedLocationWithUserName visitedLocationWithUserName) {
		visitedLocationsWithUserNameList.add(visitedLocationWithUserName);
		return visitedLocationWithUserName;
	}

	@Override
	public VisitedLocationWithUserName findByUUIDOrderByTimeVisitedDesc(UUID userId) {
		List<VisitedLocationWithUserName> visitedLocationByUUID = findByUUID(userId);
		
		if (!visitedLocationByUUID.isEmpty()) {
			VisitedLocationWithUserName visitedLocationLast = visitedLocationByUUID.get(0);
			for (VisitedLocationWithUserName visitedLocationWithUserName : visitedLocationByUUID) {
				if (visitedLocationLast.getTimeVisited().before(visitedLocationWithUserName.getTimeVisited())) {
					visitedLocationLast=visitedLocationWithUserName;
				}
			}
			return visitedLocationLast;
		}
		else {
			return null;
		}
	}

	@Override
	public VisitedLocation findByUserNameOrderByTimeVisitedDesc(String userName) {
		List<VisitedLocationWithUserName> visitedLocationByUUID = findByUserName(userName);
		if (!visitedLocationByUUID.isEmpty()) {
			VisitedLocationWithUserName visitedLocationLast = visitedLocationByUUID.get(0);
			for (VisitedLocationWithUserName visitedLocationWithUserName : visitedLocationByUUID) {
				if (visitedLocationLast.getTimeVisited().before(visitedLocationWithUserName.getTimeVisited())) {
					visitedLocationLast=visitedLocationWithUserName;
				}
			}
			return visitedLocationLast;
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteAll() {
		visitedLocationsWithUserNameList = new ArrayList<VisitedLocationWithUserName>();
	}
}
