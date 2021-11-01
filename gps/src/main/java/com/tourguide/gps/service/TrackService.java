package com.tourguide.gps.service;

import java.util.UUID;

/**
 * Track service
 * @author David
 *
 */
public interface TrackService {

	/**
	 * Used to get the visited location of user
	 * @param userName The name of user
	 * @return VisitedLocation The visited location of user
	 */
	void trackUserLocation(UUID userId, String userName);

}
