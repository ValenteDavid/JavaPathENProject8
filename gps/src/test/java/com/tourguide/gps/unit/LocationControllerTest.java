package com.tourguide.gps.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tourguide.gps.GpsApplication;
import com.tourguide.gps.controller.dto.UserIdName;
import com.tourguide.gps.proxies.UserProxy;
import com.tourguide.gps.service.GpsService;

import gpsUtil.location.Location;

@SpringBootTest(classes = GpsApplication.class)
@AutoConfigureMockMvc
public class LocationControllerTest {
	
	@MockBean
	private GpsService gpsService;
	@MockBean
	private UserProxy userProxy;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllCurrentLocationsTest() throws Exception {
		List<UserIdName> userIdNameList = new ArrayList<UserIdName>();
		userIdNameList.add(new UserIdName(UUID.nameUUIDFromBytes("internalUser0".getBytes()), "internalUser0"));
		userIdNameList.add(new UserIdName(UUID.nameUUIDFromBytes("internalUser1".getBytes()), "internalUser1"));
		when(userProxy.getUserIdAndUserNameAll()).thenReturn(userIdNameList);
		
		when(gpsService.getLastLocation("internalUser0")).thenReturn(new Location(0, 0));
		when(gpsService.getLastLocation("internalUser1")).thenReturn(new Location(10, 10));
		
		mockMvc.perform(get("/getAllCurrentLocations"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[*]userId").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[*]location").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[*]location.longitude").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[*]location.latitude").isNotEmpty())
		
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0]userId")
				.value(UUID.nameUUIDFromBytes("internalUser0".getBytes()).toString()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[1]userId")
				.value(UUID.nameUUIDFromBytes("internalUser1".getBytes()).toString()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0]location.longitude")
				.value(0))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0]location.longitude")
				.value(0))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[1]location.longitude")
				.value(10))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[1]location.longitude")
				.value(10))
		;
		
	}
}
