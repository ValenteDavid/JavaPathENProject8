package com.tourguide.testTourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gps", url = "localhost:9000")
public interface TripProxy {

	@RequestMapping("/getTripDeals")
	public String getTripDeals(@RequestParam String userName);

}
