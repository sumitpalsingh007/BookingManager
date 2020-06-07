/**
 * 
 */
package com.akqa.india.exceptions;

/**
 * @author SUMIT
 * This exception to be thrown when the user supplied booking detail are not in expected formatt
 */
public class InvalidBookingDetailsFormatException extends Exception {

	/**
	 * @param message
	 * Constructor to initialize the exception
	 */
	public InvalidBookingDetailsFormatException(String message) {
		super(message);
	}

}
