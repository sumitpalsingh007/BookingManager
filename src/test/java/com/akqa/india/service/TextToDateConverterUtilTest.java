package com.akqa.india.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TextToDateConverterUtilTest {
	
	TextToDateConverterUtil textToDateConverterUtil;
	Date date1;
	Date date2;
	Date date3;
	Date date4;
	Date date5;
	Date date6;
	Date date7;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	@Before
	public void setUp() throws Exception {
		textToDateConverterUtil = new TextToDateConverterUtil();
		//date1 = sdf.parse("2011-03-17 10:17:06");
		date1 =new Date();
		date1.setDate(17);
		date1.setMonth(02);
		date1.setYear(111);
		date1.setSeconds(06);
		date1.setHours(10);
		date1.setMinutes(17);
		//date2 = sdf.parse("2011-03-16 12:34:56");
		date2 =new Date();
		date2.setDate(16);
		date2.setMonth(02);
		date2.setYear(111);
		date2.setSeconds(56);
		date2.setHours(12);
		date2.setMinutes(34);
		//date3 = sdf.parse("2011-03-16 09:28:23");
		date3 =new Date();
		date3.setDate(16);
		date3.setMonth(02);
		date3.setYear(111);
		date3.setSeconds(23);
		date3.setHours(9);
		date3.setMinutes(28);
		//date4 = sdf.parse("2011-03-17 11:23:45");
		date4 =new Date();
		date4.setDate(17);
		date4.setMonth(02);
		date4.setYear(111);
		date4.setSeconds(45);
		date4.setHours(11);
		date4.setMinutes(23);
		//date5 = sdf.parse("2011-03-15 17:29:12");
		date5 =new Date();
		date5.setDate(15);
		date5.setMonth(02);
		date5.setYear(111);
		date5.setSeconds(12);
		date5.setHours(17);
		date5.setMinutes(29);
		
		date6 =new Date();
		date6.setDate(21);
		date6.setMonth(02);
		date6.setYear(111);
		date6.setSeconds(0);
		date6.setHours(9);
		date6.setMinutes(00);
		
		date7 =new Date();
		date7.setDate(22);
		date7.setMonth(02);
		date7.setYear(111);
		date7.setSeconds(0);
		date7.setHours(14);
		date7.setMinutes(0);
		
		Date date8=new Date();
		Date date9=new Date();
		Date date10=new Date();
	}

	@After
	public void tearDown() throws Exception {
		textToDateConverterUtil = null;
		date1 = null;
		date2 = null;
		date3 = null;
		date4 = null;
		date5 = null;
		date6 = null;
		date7 = null;
	}

	@Test
	public void testgetDateFromStringWithTime() {
		try {
			Assert.assertEquals(sdf.format(date1), sdf.format(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1710:17:06")));
			Assert.assertEquals(sdf.format(date2), sdf.format(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1612:34:56")));
			Assert.assertEquals(sdf.format(date3), sdf.format(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1609:28:23")));
			Assert.assertEquals(sdf.format(date4), sdf.format(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1711:23:45")));
			Assert.assertEquals(sdf.format(date5), sdf.format(TextToDateConverterUtil.getDateFromStringWithTime("2011-03-1517:29:12")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	public void getDateFromStringWithTimeNoSeconds() {
		try {
			
			Assert.assertEquals(sdf.format(date6), sdf.format(TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2109:00")));
			Assert.assertEquals(sdf.format(date7), sdf.format(TextToDateConverterUtil.getDateFromStringWithTimeNoSeconds("2011-03-2214:00")));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
