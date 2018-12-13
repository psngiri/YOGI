package yogi.report.server.sql.test;

import junit.framework.TestCase;

import yogi.report.server.sql.SqlConditions;

public class QueryUtilTest extends TestCase{

	public void test(){
		try {
			SqlConditions.valueOf("TEST");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
