package yogi.base.util.test;

import junit.framework.TestCase;

import yogi.base.io.resource.db.msaccess.MSAccessDbResource;
import yogi.base.util.DbPorter;

public class DbPorterTestWindows  extends TestCase {

	
	public void testPort()
	{
		String fromQuery = "select ID, Airline, InFlightNumber, OutFlightNumber, ArrivalTime, DepartureTime, UplineCity, DownlineCity, DOP, AircraftType, EffectiveDate, DiscontinueDate from MasterSchedule";
		String toQuery = "insert into  MasterSchedule (ID, Airline, InFlightNumber, OutFlightNumber, ArrivalTime, DepartureTime, UplineCity, DownlineCity, DOP, AircraftType, EffectiveDate, DiscontinueDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		MSAccessDbResource fromDbResource = MSAccessDbResource.getDbResource("data/exampleTestData/MasterSchedule.mdb");
		MSAccessDbResource toDbResource = MSAccessDbResource.getDbResource("data/exampleTestData/MasterSchedule1.mdb");
		DbPorter.port(fromDbResource, fromQuery, toDbResource, toQuery, 10);
	}
}
