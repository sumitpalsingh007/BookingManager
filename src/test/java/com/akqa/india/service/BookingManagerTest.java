package com.akqa.india.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.akqa.india.model.Booking;

public class BookingManagerTest {

	BookingManager bookingManager;
	Booking booking1;
	Booking booking2;
	Booking booking3;
	Booking booking4;
	Booking booking5;
	Booking booking6;
	List<Booking> recievedBookings;
	
	@Before
	public void setUp() throws Exception {
		booking1 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1710:17:06")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2109:00")
				,2,"EMP001");
		booking2 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1612:34:56")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2109:00")
				,2,"EMP002");
		booking3 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1609:28:23")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2214:00")
				,2,"EMP003");
		booking4 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1711:23:45")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2216:00")
				,1,"EMP004");
		booking5 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1517:29:12")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2116:00")
				,3,"EMP005");
		booking6 = new Booking(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1517:31:12")
				,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2116:00")
				,3,"EMP006");
		recievedBookings = new ArrayList();
		
	}

	@After
	public void tearDown() throws Exception {
		 bookingManager = null;
		 booking1 = null;
		 booking2 = null;
		 booking3 = null;
		 booking4 = null;
		 booking5 = null;
		 booking6 = null;
		 recievedBookings = null;
	}

	@Test
	public void testCheckAvailablebookings() {
		//recievedBookings.add(booking2);
		BookingManager.getConfirmedBookings().add(booking2);		
		Assert.assertEquals(false, BookingManager.checkAvailablebookings(booking1));

		BookingManager.getConfirmedBookings().add(booking4);		
		Assert.assertEquals(true, BookingManager.checkAvailablebookings(booking5));
	}

	@Test
	public void testCheckValidTimings() {
		Date startDate = new Date();
		startDate.setHours(9);
		startDate.setMinutes(0);
		Date endDate = new Date();
		endDate.setHours(17);
		endDate.setMinutes(30);
		BookingManager.setOfficeStartTime(startDate);
		BookingManager.setOfficeEndTime(endDate);
		//Assert.assertEquals(false, BookingManager.checkValidTimings(booking6));
		Assert.assertEquals(false, BookingManager.checkValidTimings(booking5));
		Assert.assertEquals(true, BookingManager.checkValidTimings(booking4));
		Assert.assertEquals(true, BookingManager.checkValidTimings(booking3));
		Assert.assertEquals(true, BookingManager.checkValidTimings(booking2));
		Assert.assertEquals(true, BookingManager.checkValidTimings(booking1));
	}

}
