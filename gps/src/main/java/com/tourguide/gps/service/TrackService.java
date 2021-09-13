package com.tourguide.gps.service;

import gpsUtil.location.VisitedLocation;

public interface TrackService {

	VisitedLocation trackUserLocation(String userName);

}
