package com.tourguide.gps.proxies;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.gps.controller.dto.UserIdName;

/**
 * User proxy
 * @author David
 * @see com.tourguide.user.controller.UserController
 *
 */
@FeignClient(name = "user", url = "localhost:9003")
public interface UserProxy {
	
	@RequestMapping("/getUserId")
	UUID getUserId(@RequestParam String userName);
	
	@RequestMapping("/getUserIdAndUserNameAll")
	List<UserIdName> getUserIdAndUserNameAll();

}
