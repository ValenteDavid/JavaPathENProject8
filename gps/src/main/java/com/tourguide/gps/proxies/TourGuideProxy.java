package com.tourguide.gps.proxies;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.beans.VisitedLocationBean;

@FeignClient(name = "TourGuide", url = "localhost:8080")
public interface TourGuideProxy {
	
	@RequestMapping("/getVisitedLocations")
	List<VisitedLocationBean> getVisitedLocations(@RequestParam String userName);

	@RequestMapping("/getLastVisitedLocation")
	VisitedLocationBean getLastVisitedLocation(@RequestParam String userName);

	@RequestMapping("/trackUserLocation")
	VisitedLocationBean trackUserLocation(@RequestParam String userName);

}
