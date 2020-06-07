package com.akqa.india.service;

import java.util.Comparator;

import com.akqa.india.model.Booking;

/**
 * @author SUMIT
 * Comparator to compare the bookings on the basis of start time
 */
public class BookingStartTimeComparator implements Comparator<Booking> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Booking booking1, Booking booking2) {

		return booking1.getStartTime().compareTo(booking2.getStartTime());
		
	}

}
