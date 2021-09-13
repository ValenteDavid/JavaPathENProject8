package com.tourguide.gps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;

@Service
public class AttractionServiceImpl implements AttractionService {
	
	@Autowired
	private GpsUtil gpsUtil;

	@Override
	public List<Attraction> getAttractions() {
		return gpsUtil.getAttractions();
	}

}
