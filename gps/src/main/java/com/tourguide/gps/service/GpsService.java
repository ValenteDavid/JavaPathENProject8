package com.tourguide.gps.service;

import java.util.UUID;

import gpsUtil.location.VisitedLocation;

public interface GpsService {

	VisitedLocation getUserLocation(UUID uuid);

}
