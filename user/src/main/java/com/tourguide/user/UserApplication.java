package com.tourguide.user;

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
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.tourguide.user.dao.UserDao;
import com.tourguide.user.dao.UserPreferenceDao;
import com.tourguide.user.domain.User;
import com.tourguide.user.domain.UserPreferences;
import com.tourguide.user.helper.InternalTestHelper;

/**
 * User Application
 * @author David
 *
 */
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
	private UserPreferenceDao userPreferenceDao;

	@Profile("dev")
	@Bean
	CommandLineRunner runner(Environment env) {
		return args -> {
			
			IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {

				// Create and Save User
				String userName = "internalUser" + i;
				String phone = "000";
				String email = userName + "@tourGuide.com";
				UUID userId = UUID.nameUUIDFromBytes(userName.getBytes());
				User user = new User(userId, userName, phone, email);
				logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
				userDao.save(user);
				
				userPreferenceDao.save(new UserPreferences(userId, userName,1, 1, 5, 5));

			});
		};
		
	}
}
