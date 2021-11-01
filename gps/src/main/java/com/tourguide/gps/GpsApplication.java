package com.tourguide.gps;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.tourguide.gps.dao.VisitedLocationDao;
import com.tourguide.gps.domain.VisitedLocationWithUserName;
import com.tourguide.gps.helper.InternalTestHelper;

import gpsUtil.location.Location;
/**
 * Gps Application
 * @author David
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class GpsApplication{

	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}

	@Autowired
	private VisitedLocationDao visitedLocationDao;

	@Profile("dev")
	@Bean
	CommandLineRunner runner(Environment env) {
		return args -> {
			IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
				String userName = "internalUser" + i;

				IntStream.range(0, 3).forEach(j -> {
					visitedLocationDao.save(
							new VisitedLocationWithUserName(
									null,
									UUID.nameUUIDFromBytes(userName.getBytes()),
									userName,
									new Location(generateRandomLatitude(), generateRandomLongitude()),
									getRandomTime()));
				});
			});

		};

	}

	private double generateRandomLongitude() {
		double leftLimit = -180;
		double rightLimit = 180;
		return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}

	private double generateRandomLatitude() {
		double leftLimit = -85.05112878;
		double rightLimit = 85.05112878;
		return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}

	private Date getRandomTime() {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
		return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
	}

}
