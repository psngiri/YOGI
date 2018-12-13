package yogi.period.date.io.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.io.YYMMMDDPartialDateScanner;

public class YYMMMDDPartialDateScannerTest extends TestCase {
	
	public void testYYMMMDDPartialDate() {
		YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
		dateScanner.setEndDate(false);
		String dateString;
		Date date;
		
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JANUARY, 20), date);
		
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JANUARY, 21), date);
		
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JANUARY, 22), date);
		
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 20), date);
		
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 21), date);
		
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 22), date);
		
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
		
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20Feb";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
		
		dateString = "21feb";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22fEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 22), date);
		
		
		dateScanner.setEndDate(true);
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.FEBRUARY, 20), date);
				
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JANUARY, 21), date);
		
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN12").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(12, Calendar.JANUARY, 22), date);
		
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
		
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 21), date);
		
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 22), date);
		
		dateString = "20";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.MARCH, 20), date);
		
		dateString = "21";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
				
		dateString = "20FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 20), date);
				
		dateString = "21FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21JAN13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(13, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 22), date);
		
		dateString = "20Feb";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 20), date);
		
		dateString = "21feb";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 21), date);
				
		dateString = "22fEB";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21MAR13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 22), date);
		
		dateString = "20FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 20), date);
		
		dateString = "21FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 21), date);
				
		dateString = "22FEB14";
		dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("21FEB13").create());
		date = dateScanner.scan(dateString).create();
		System.out.println("Start Date \t: " + date);
		assertEquals(DateManager.get().getDate(14, Calendar.FEBRUARY, 22), date);
			
	}
}
