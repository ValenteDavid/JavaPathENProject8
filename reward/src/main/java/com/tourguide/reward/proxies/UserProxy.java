package com.tourguide.reward.proxies;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tourguide.reward.controller.dto.UserIdName;

/**
 * User proxy
 * @author David
 * @see com.tourguide.user.controller.UserController
 *
 */
@FeignClient(name = "user", url = "localhost:9003")
public interface UserProxy {
	
	/**
	 * @see com.tourguide.user.controller.UserController
	 */
	@RequestMapping("/getUserId")
	UUID getUserId(@RequestParam String userName);
	
	/**
	 * @see com.tourguide.user.controller.UserController
	 */
	@RequestMapping("/getUserIdAndUserNameAll")
	List<UserIdName> getUserIdAndUserNameAll();

}
