package com.tourguide.gps.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "localhost:9003")
public interface UserProxy {
	
	@RequestMapping("/getUserId")
	UUID getUserId(@RequestParam String userName);

}
