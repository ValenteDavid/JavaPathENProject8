package tourGuide.dto;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gpsUtil.location.VisitedLocation;

public class VisitedLocationDto {

	private static final Logger log = LoggerFactory.getLogger(VisitedLocationDto.class);

	private final UUID userId;
	private final double longitude;
	private final double latitude;
	private final Date timeVisited;

	public VisitedLocationDto(UUID userId, double longitude, double latitude, Date timeVisited) {
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timeVisited = timeVisited;
	}

	public UUID getUserId() {
		return userId;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}

	public static VisitedLocationDto convertToDTO(VisitedLocation visitedLocation) {
		return new VisitedLocationDto(
				visitedLocation.userId,
				visitedLocation.location.longitude,
				visitedLocation.location.latitude,
				visitedLocation.timeVisited);
	}

}
