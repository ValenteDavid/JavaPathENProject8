package tourGuide;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourGuide.service.RewardsService;

@Configuration
public class TourGuideModule {

	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtilTemp();
	}

	@Bean
	public RewardsService getRewardsService() {
		return new RewardsService(getGpsUtil(), getRewardCentral());
	}

	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}

	private class GpsUtilTemp extends GpsUtil {
		private final RateLimiter rateLimiterTemps = RateLimiter.create(1000.0D);

		@Override
		public VisitedLocation getUserLocation(UUID userId) {
			rateLimiterTemps.acquire();
			this.sleep();

			double longitude = ThreadLocalRandom.current().nextDouble(-180.0D, 180.0D);
//			longitude = Double.parseDouble(String.format("%.6f", longitude));
			double latitude = ThreadLocalRandom.current().nextDouble(-85.05112878D, 85.05112878D);
//			latitude = Double.parseDouble(String.format("%.6f", latitude));

			VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(latitude, longitude),
					new Date());

			return visitedLocation;
		}

		private void sleep() {
			int random = ThreadLocalRandom.current().nextInt(30, 100);
			try {
				TimeUnit.MILLISECONDS.sleep((long) random);
			} catch (InterruptedException var3) {
				;
			}
		}

	}

}
