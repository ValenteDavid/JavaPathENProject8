package com.tourguide.gps.dao;

import java.util.List;
import java.util.UUID;

import com.tourguide.gps.domain.VisitedLocationWithUserName;

import gpsUtil.location.VisitedLocation;

public interface VisitedLocationDao {

	List<VisitedLocationWithUserName> findByUUID(UUID userId);

	VisitedLocationWithUserName save(VisitedLocationWithUserName visitedLocationWithUserName);

	VisitedLocationWithUserName findByUUIDOrderByTimeVisitedDesc(UUID userId);

	List<VisitedLocationWithUserName> findByUserName(String userName);

	VisitedLocation findByUserNameOrderByTimeVisitedDesc(String userName);

	List<VisitedLocationWithUserName> getVisitedLocationsWithUserNameList();

	void setVisitedLocationsWithUserNameList(List<VisitedLocationWithUserName> visitedLocationsWithUserNameList);

	void deleteAll();

}
