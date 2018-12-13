package yogi.period.date.io.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.io.YYMMDDPartialDateScanner;

public class YYMMDDPartialDateScannerTest extends TestCase {
	
	public void testStartDate() {
		
		YYMMDDPartialDateScanner dateScanner = new YYMMDDPartialDateScanner();
		dateScanner.setEndDate(false);
		
		String dateString;
		Date date;
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);

		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);

		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);

		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);

		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);
		
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 01), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 01), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 01), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 01), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 01), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 01), date);
		
		
		
		dateString = "01";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 01), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.FEBRUARY, 01), date);

		dateString = "03";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.MARCH, 01), date);

		dateString = "04";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.APRIL, 01), date);

		dateString = "05";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.MAY, 01), date);

		dateString = "06";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JUNE, 01), date);

		dateString = "07";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JULY, 01), date);

		dateString = "08";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.AUGUST, 01), date);

		dateString = "09";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.SEPTEMBER, 01), date);

		dateString = "10";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.OCTOBER, 01), date);

		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 01), date);

		dateString = "12";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.DECEMBER, 01), date);
		
	}
			
	public void testEndDate() {	
		YYMMDDPartialDateScanner dateScanner = new YYMMDDPartialDateScanner();
		dateScanner.setEndDate(true);
		String dateString;
		Date date;
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "121105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 05), date);

		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);

		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);
		
		dateString = "1105";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 05), date);
		
		
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 30), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01OCT12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 30), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 30), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("11NOV12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 30), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01DEC12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 30), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.NOVEMBER, 30), date);
		
		dateString = "01";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 31), date);
		
		dateString = "01";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("31JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 31), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.FEBRUARY, 29), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 28), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN14").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 28), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN15").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(15, Calendar.FEBRUARY, 28), date);
		
		dateString = "02";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN16").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(16, Calendar.FEBRUARY, 29), date);
		
		dateString = "03";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.MARCH, 31), date);
				
		dateString = "04";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.APRIL, 30), date);
		
		dateString = "05";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.MAY, 31), date);
		
		dateString = "06";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JUNE, 30), date);
		
		dateString = "07";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JULY, 31), date);
		
		dateString = "08";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.AUGUST, 31), date);
		
		dateString = "09";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.SEPTEMBER, 30), date);
		
		dateString = "10";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.OCTOBER, 31), date);
		
		dateString = "11";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.NOVEMBER, 30), date);
		
		dateString = "12";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("01JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("End Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.DECEMBER, 31), date);
	}
	
}
