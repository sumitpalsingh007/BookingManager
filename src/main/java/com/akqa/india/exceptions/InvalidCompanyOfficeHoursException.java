/**
 * 
 */
package com.akqa.india.exceptions;

/**
 * @author SUMIT
 * This exception to be thrown when the user supplied company office hours detail are not in expected formatt
 */
public class InvalidCompanyOfficeHoursException extends Exception {

	/**
	 * @param message
	 * Constructor to initialize the exception
	 */
	public InvalidCompanyOfficeHoursException(String message) {
		super(message);
	}

}
