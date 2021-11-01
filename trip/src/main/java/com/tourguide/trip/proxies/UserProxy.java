package com.tourguide.trip.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.trip.controller.dto.UserPreferenceDto;

/**
 * User proxy
 * @author David
 * @see com.tourguide.user.controller.UserController
 */
@FeignClient(name = "user", url = "localhost:9003")
public interface UserProxy {
	
	/**
	 * @see com.tourguide.user.controller.UserController
	 */
	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);
	
	/**
	 * @see com.tourguide.user.controller.UserController
	 */
	@RequestMapping("/getUserPreference")
	public UserPreferenceDto getUserPreference(@RequestParam String userName);

}
