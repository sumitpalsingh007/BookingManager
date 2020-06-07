package com.akqa.india.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author SUMIT
 * Util class to convert texts to date objects
 */
public class TextToDateConverterUtil {
	
	/**
	 * Date formatter for received time of booking
	 */
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
	/**
	 * Date formatter for start and end time of booking
	 */
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");
	
	/**
	 * @param inputDate
	 * @return Date
	 * @throws ParseException
	 * Method to format received time of booking
	 */
	public static Date getDateFromStringWithTime(String inputDate) throws ParseException{
		return sdf.parse(inputDate);
	}
	
	/**
	 * @param inputDate
	 * @return Date
	 * @throws ParseException
	 * Method to format start and end time of booking
	 */
	public static Date getDateFromStringWithTimeNoSeconds(String inputDate) throws ParseException{
		return sdf1.parse(inputDate);
	}
	
	
}
