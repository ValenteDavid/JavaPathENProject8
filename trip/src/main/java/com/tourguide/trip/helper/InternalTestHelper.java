package com.tourguide.trip.helper;

/**
 * Use for Test
 *
 */
public class InternalTestHelper {

	// Set this default up to 100,000 for testing
	private static int internalUserNumber = 500;
	
	public static void setInternalUserNumber(int internalUserNumber) {
		InternalTestHelper.internalUserNumber = internalUserNumber;
	}
	
	public static int getInternalUserNumber() {
		return internalUserNumber;
	}
}
