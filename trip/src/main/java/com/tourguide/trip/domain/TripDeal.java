package com.tourguide.trip.domain;

import java.util.List;
import java.util.UUID;

import tripPricer.Provider;

/**
 *   Trip Deal                            
 * @author David
 *
 */
public class TripDeal {
	
	/**
	 * user id
	 */
	private final UUID userId;
	/**
	 * user name
	 */
	private final String userName;
	/**
	 * provider
	 * @see Provider
	 */
	List<Provider> provider;
	
	public TripDeal(UUID userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public TripDeal(UUID userId, String userName, List<Provider> provider) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.provider = provider;
	}

	public List<Provider> getProvider() {
		return provider;
	}

	public void setProvider(List<Provider> provider) {
		this.provider = provider;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
}
