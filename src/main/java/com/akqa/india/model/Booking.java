package com.akqa.india.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author SUMIT
 * This is a model class representing a booking. It has all properties of a booking like received time, start time, end time 
 * and requester
 */
/**
 * @author SUMIT
 *
 */
public class Booking {
	
	/**
	 * @param requestTime
	 * @param startTime
	 * @param duration
	 * @param requester
	 * Constructor to initialize a booking
	 */
	public Booking(Date requestTime, Date startTime, int duration, String requester){
		this.requestTime=requestTime;
		this.startTime = startTime;
		Calendar cal = Calendar.getInstance(); 
	    cal.setTime(startTime); 
	    cal.add(Calendar.HOUR_OF_DAY, duration); 
		this.endTime = cal.getTime();
		this.requester = requester;
	}
	
	/**
	 * Time at which booking was recieved
	 */
	private Date requestTime;
	/**
	 * Start time of Booking
	 */
	private Date startTime;
	/**
	 * End time of booking
	 */
	private Date endTime;
	/**
	 * Employeed Id of person requesting this booking
	 */
	private String requester;
	
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	
	@Override
	public String toString(){
		return new String( startTime.getHours() + ":" +startTime.getMinutes()+" "+
	                       endTime.getHours()+":"+endTime.getMinutes() +" " +requester);
    }
	
	@Override
	public boolean equals(Object o){
		// TODO equals implementation
		return false;
	}
	

}
