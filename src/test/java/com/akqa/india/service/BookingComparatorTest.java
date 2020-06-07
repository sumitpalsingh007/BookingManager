package com.akqa.india.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.akqa.india.model.Booking;

public class BookingComparatorTest {
	
	BookingRecieveTimeComparator bookingComparator;
	BookingStartTimeComparator bookingStartTimeComparator;
	Booking booking1;
	Booking booking2;
	Booking booking3;
	Booking booking4;
	Booking booking5;
	
	List<Booking> recievedBookings;

	@Before
	public void setUp() throws Exception {
		bookingComparator = new BookingRecieveTimeComparator();
		bookingStartTimeComparator = new BookingStartTimeComparator();
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
		recievedBookings = new ArrayList();
		recievedBookings.add(booking1);
		recievedBookings.add(booking2);
		recievedBookings.add(booking3);
		recievedBookings.add(booking4);
		recievedBookings.add(booking5);
		/*09001730
		2011-03-1710:17:06EMP001
		2011-03-2109:002
		
		2011-03-1612:34:56EMP002
		2011-03-2109:002
		
		2011-03-1609:28:23EMP003
		2011-03-2214:002
		
		2011-03-1711:23:45EMP004
		2011-03-2216:001
		
		2011-03-1517:29:12EMP005
		2011-03-2116:003
		O*/

	}

	@After
	public void tearDown() throws Exception {
		bookingComparator = null;
	}

	@Test
	public void testRecieveTimeCompare() {
		
		Collections.sort(recievedBookings,bookingComparator);
		Assert.assertEquals("EMP005", recievedBookings.get(0).getRequester());
		Assert.assertEquals("EMP003", recievedBookings.get(1).getRequester());
		Assert.assertEquals("EMP002", recievedBookings.get(2).getRequester());
		Assert.assertEquals("EMP001", recievedBookings.get(3).getRequester());
	}
	
	@Test
	public void testStartTimeCompare() {
		
		Collections.sort(recievedBookings,bookingStartTimeComparator);
		Assert.assertEquals("EMP005", recievedBookings.get(2).getRequester());
		Assert.assertEquals("EMP003", recievedBookings.get(3).getRequester());
		Assert.assertEquals("EMP004", recievedBookings.get(4).getRequester());
		//Assert.assertEquals("EMP001", recievedBookings.get(3).getRequester());
	}

}
