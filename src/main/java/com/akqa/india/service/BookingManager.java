package com.akqa.india.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.akqa.india.model.Booking;

/**
 * @author SUMIT
 * Class containing all the implementation logic for validating, assigning bookings and other related operations
 */
public class BookingManager {

	/**
	 * List of all the bookings requested by the user
	 */
	private static List<Booking> recievedBookings;
	/**
	 * List of all the bookings confirmed by the system after processing
	 */
	private static List<Booking> confirmedBookings;
	/**
	 * Office starting timing. Note:- Only time part is used and date is ignored
	 */
	private static Date officeStartTime;
	/**
	 * Office closing timing. Note:- Only time part is used and date is ignored
	 */
	private static Date officeEndTime;
	/**
	 * Date formatter to extract time for date object
	 */
	private static SimpleDateFormat sdf; 
	/**
	 * Date formatter the extract date from date object
	 */
	private static SimpleDateFormat sdf1;
	
	static{
		recievedBookings =new ArrayList();	
		confirmedBookings = new ArrayList();
		sdf = new SimpleDateFormat("HH:MM");
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * @param requestedBooking
	 * @return boolean
	 * This method checks whether a booking can be made or not on the basis of already made bookings and office hours
	 */
	public static boolean checkAvailablebookings(Booking requestedBooking) {
		boolean availablity = true;
		for(Booking booking :confirmedBookings){
			
			if(booking.getStartTime().equals(requestedBooking.getStartTime())
					||booking.getEndTime().equals(requestedBooking.getEndTime())){
				availablity= false;
			}
			else if(booking.getStartTime().after(requestedBooking.getStartTime())
					&& booking.getEndTime().before(requestedBooking.getEndTime())){
				availablity= false;
			}
			else if(booking.getStartTime().before(requestedBooking.getStartTime())
					&& booking.getEndTime().after(requestedBooking.getEndTime())){
				availablity= false;
			}
			
         }
		return availablity;
	}
	
	/**
	 * @param requestedBooking
	 * @return boolean
	 * This method checks whether booking start time and end time lie between office working hours or not
	 */
	public static boolean checkValidTimings(Booking requestedBooking){
		
		if(officeStartTime.getHours()<requestedBooking.getStartTime().getHours()
				&& officeEndTime.getHours()>requestedBooking.getEndTime().getHours() ){
			return true;
		}
		else if(officeStartTime.getHours()==requestedBooking.getStartTime().getHours()
				&& officeStartTime.getMinutes()<=requestedBooking.getStartTime().getMinutes()	){
			return true;
		}
		else if (officeEndTime.getHours()==requestedBooking.getEndTime().getHours()
				&& officeEndTime.getMinutes()>=requestedBooking.getEndTime().getMinutes()){
			return true;
		}
		return false;
	}

	/**
	 * This method is used to finally print the confirmed bookings in the expected format
	 */
	public static void printConfirmedBookings(){
		String date = null;
		for(Booking booking:confirmedBookings){
			if(!sdf1.format(booking.getStartTime()).equals(date)){
				date=sdf1.format(booking.getStartTime());
				System.out.println(date);
			}
			System.out.println(booking);
		}
	}
	
	/**
	 * @param booking
	 * This method is used to finally assign a booking after all the checks are done
	 */
	public static void assignBooking(Booking booking) {
		confirmedBookings.add(booking);
	}

	/**
	 * @return
	 * Accessor method the access the received bookings
	 */
	public static List<Booking> getRecievedBookings() {
		return recievedBookings;
	}

	/**
	 * @return
	 * Accessor method the access the confirmed bookings
	 */
	public static List<Booking> getConfirmedBookings() {
		return confirmedBookings;
	}

	/**
	 * @return
	 * Office start time gettor
	 */
	public static Date getOfficeStartTime() {
		return officeStartTime;
	}

	/**
	 * @param officeStartTime
	 * Office start time settor
	 */
	public static void setOfficeStartTime(Date officeStartTime) {
		BookingManager.officeStartTime = officeStartTime;
	}

	/**
	 * @return Date
	 * Office end time getter
	 */
	public static Date getOfficeEndTime() {
		return officeEndTime;
	}

	/**
	 * @param officeEndTime
	 * Office end time settor
	 */
	public static void setOfficeEndTime(Date officeEndTime) {
		BookingManager.officeEndTime = officeEndTime;
	}

}
