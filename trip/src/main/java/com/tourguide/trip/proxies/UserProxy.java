package com.tourguide.trip.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.trip.controller.dto.UserPreferenceDto;

@FeignClient(name = "user", url = "localhost:9003")
public interface UserProxy {
	
	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);
	
	@RequestMapping("/getUserPreference")
	public UserPreferenceDto getUserPreference(@RequestParam String userName);

}
