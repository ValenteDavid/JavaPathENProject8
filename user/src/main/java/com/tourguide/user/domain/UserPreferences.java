package com.tourguide.user.domain;

import java.util.UUID;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;

/**
 * User preferences
 *
 */
public class UserPreferences {
	
	/**
	 * user id
	 * @see User
	 */
	private final UUID userId;
	/**
	 * user name
	 * @see User
	 */
	private final String userName;
	private int attractionProximity = Integer.MAX_VALUE;
	private CurrencyUnit currency = Monetary.getCurrency("USD");
	private Money lowerPricePoint = Money.of(0, currency);
	private Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);
	/**
	 * trip duration
	 */
	private int tripDuration = 1;
	/**
	 * ticket quantity
	 */
	private int ticketQuantity = 1;
	/**
	 * number of adultes
	 */
	private int numberOfAdults = 1;
	/**
	 * number of children
	 */
	private int numberOfChildren = 0;
	
	public UserPreferences(UUID userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public UserPreferences(UUID userId, String userName, int tripDuration, int ticketQuantity, int numberOfAdults,
			int numberOfChildren) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.tripDuration = tripDuration;
		this.ticketQuantity = ticketQuantity;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}



	public UUID getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setAttractionProximity(int attractionProximity) {
		this.attractionProximity = attractionProximity;
	}
	
	public int getAttractionProximity() {
		return attractionProximity;
	}
	
	public Money getLowerPricePoint() {
		return lowerPricePoint;
	}

	public void setLowerPricePoint(Money lowerPricePoint) {
		this.lowerPricePoint = lowerPricePoint;
	}

	public Money getHighPricePoint() {
		return highPricePoint;
	}

	public void setHighPricePoint(Money highPricePoint) {
		this.highPricePoint = highPricePoint;
	}
	
	public int getTripDuration() {
		return tripDuration;
	}

	public void setTripDuration(int tripDuration) {
		this.tripDuration = tripDuration;
	}

	public int getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(int ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}
	
	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

}
