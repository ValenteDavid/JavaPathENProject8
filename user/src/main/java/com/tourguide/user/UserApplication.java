package com.tourguide.user;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.tourguide.user.dao.UserDao;
import com.tourguide.user.domain.User;
import com.tourguide.user.helper.InternalTestHelper;
import com.tourguide.user.proxies.GpsProxy;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class UserApplication {

	private static final Logger logger = LoggerFactory.getLogger(UserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Autowired
	private UserDao userDao;
	@Autowired
	private GpsProxy gpsProxy;

	@Bean
	CommandLineRunner runner(Environment env) {
		return args -> {
			
			IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {

				// Create and Save User
				String userName = "internalUser" + i;
				String phone = "000";
				String email = userName + "@tourGuide.com";
				User user = new User(UUID.randomUUID(), userName, phone, email);
				logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
				userDao.save(user);

				if (i != 5) {
					// Create VisitedLocation associate user
					IntStream.range(0, 3).forEach(j -> {
						gpsProxy.addVisitedLocation(
								user.getUserId(),
								user.getUserName(),
								generateRandomLatitude(),
								generateRandomLongitude(),
//							getRandomTime());
								new Date());
					});
				}
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
