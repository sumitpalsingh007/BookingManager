package com.akqa.india.launch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.akqa.india.exceptions.InvalidCompanyOfficeHoursException;
import com.akqa.india.model.Booking;
import com.akqa.india.service.BookingManager;
import com.akqa.india.service.BookingRecieveTimeComparator;
import com.akqa.india.service.BookingStartTimeComparator;
import com.akqa.india.service.TextToDateConverterUtil;


/**
 * @author SUMIT
 * Main class the launch the application
 */
public class Launcher 
{
	/**
	 * String containing relative path of file used to supply input to the application
	 */
	private static final String INPUT_FILE_PATH = "..\\BookingManager\\src\\main\\resources\\inputFile.txt";
	/**
	 * Comparator to sort the booking requests on the basis of recieved time
	 */
	private static final BookingRecieveTimeComparator bookingRecieveTimeComparator = new BookingRecieveTimeComparator();
	/**
	 * Comparator used to sort the bookings according to their start time
	 */
	private static final BookingStartTimeComparator bookingStartTimeComparator = new BookingStartTimeComparator();
	
	/**
	 * @param args
	 * @throws InvalidCompanyOfficeHoursException
	 * Main method to launch the application
	 */
	public static void main( String[] args ) throws InvalidCompanyOfficeHoursException
    {
		System.out.println("=============================WELCOME TO BOOKING MANAGEMENT SYSTEM========================================");
		String line;
		List<String> lines = new ArrayList<String>();
		try {
		    InputStream fis = new FileInputStream(INPUT_FILE_PATH);
		    InputStreamReader isr = new InputStreamReader(fis);
		    BufferedReader br = new BufferedReader(isr);
		    String firstLine = br.readLine();
		    processFirstLine(firstLine);
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    }
		    recieveBookings(lines);
		    Collections.sort(BookingManager.getRecievedBookings(),bookingRecieveTimeComparator);
			List<Booking> recievedBookings =BookingManager.getRecievedBookings();
			for(Booking booking:recievedBookings){
				if(BookingManager.checkValidTimings(booking)
						&& BookingManager.checkAvailablebookings(booking)){
					BookingManager.assignBooking(booking);
				}
			}
		    Collections.sort(BookingManager.getConfirmedBookings(),bookingStartTimeComparator);
			BookingManager.printConfirmedBookings();
			System.out.println("=========================================================================================================");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
    }

	/**
	 * @param lines
	 * Method to create booking requests form lines of inputs form the user
	 */
	private static void recieveBookings(List<String> lines) {
		// TODO invalid input handling
		for(int i=0;i<10;i=i+2){
			try {
			String recievedDateString= lines.get(i).substring(0,18);
			String empId = lines.get(i).substring(18);
			String meetingStartTimeString =lines.get(i+1).substring(0,15);
			String meetingDuration =lines.get(i+1).substring(15);
			BookingManager.getRecievedBookings().add(new Booking(TextToDateConverterUtil.getDateFromStringWithTime(recievedDateString)
						,TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds(meetingStartTimeString)
						,Integer.valueOf(meetingDuration),empId));
			} catch (ParseException e) {
				System.out.println(lines.get(i)+ " "+ lines.get(i+1)+" not in expected format");
			} catch( IndexOutOfBoundsException e){
				System.out.println(lines.get(i)+ " "+ lines.get(i+1)+" not in expected format");
			}
			
		}
		
		
	}

	/**
	 * @param line
	 * @throws InvalidCompanyOfficeHoursException
	 * Method to initialize company working hours based on first line of input form the user
	 */
	private static void processFirstLine(String line) throws InvalidCompanyOfficeHoursException {
		if(line.length()!=8 || !line.matches("\\d+")){
			throw new InvalidCompanyOfficeHoursException(line+ " is not in expected formatt");
		}
		String[] timings =line.split("(?<=\\G.{2})");
		if(Integer.valueOf(timings[0])>24
				||Integer.valueOf(timings[2])>24
				||Integer.valueOf(timings[1])>60
				||Integer.valueOf(timings[3])>60
				||Integer.valueOf(timings[3])<Integer.valueOf(timings[0]))
		{
			throw new InvalidCompanyOfficeHoursException(line+ " is not in proper 24 hr clock formatt");
		}
		Date startDate = new Date();
		startDate.setHours(Integer.valueOf(timings[0]));
		startDate.setMinutes(Integer.valueOf(timings[1]));
		Date endDate = new Date();
		endDate.setHours(Integer.valueOf(timings[2]));
		endDate.setMinutes(Integer.valueOf(timings[3]));
		BookingManager.setOfficeStartTime(startDate);
		BookingManager.setOfficeEndTime(endDate);
	}
}
