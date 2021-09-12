package com.tourguide.gps.dao;

import java.util.List;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;

public interface VisitedLocationDao {

	List<VisitedLocation> findByUUID(UUID userId);

	VisitedLocation save(VisitedLocation visitedLocation);

	VisitedLocation findByUUIDOrderByTimeVisitedDesc(UUID userId);

}
