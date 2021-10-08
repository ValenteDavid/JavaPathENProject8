package com.tourguide.trip.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tripPricer.TripPricer;

@Configuration
public class Module {
	
	@Bean
	public TripPricer getTripPricer() {
		return new TripPricer();
	}
	
}
