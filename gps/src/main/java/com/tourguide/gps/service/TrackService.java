package com.tourguide.gps.service;

import java.util.UUID;

import gpsUtil.location.VisitedLocation;

public interface TrackService {

	VisitedLocation trackUserLocation(UUID userId, String userName);

}
