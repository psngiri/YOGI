package yogi.report.server.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportServerImpl;

public class ServerImplTestServer extends TestCase {

	public void test()
	{
		ReportServerImpl server = new ReportServerImpl();
		List<Column> columns=new ArrayList<Column>();
		columns.add(new Column("Key1", "Key1", "", false,0,2));
		columns.add(new Column("Key2", "Key2", "", true,0,1));
		columns.add(new Column("Value1", "Value1", "Sum", false,-1,0));
		List<Filter> filters=new ArrayList<Filter>();
		filters.add(new Filter("Key1","EqualsCondition","a",""));
		List<Filter> groupFilters=new ArrayList<Filter>();
		groupFilters.add(new Filter("Key1","EqualsCondition","a",""));
		Query query = new Query("DomesticPRE",null, "DomesticFares",columns,filters,groupFilters);
		@SuppressWarnings("unchecked")
		List<ReportData> items = (List<ReportData>) server.generateReport(query,-1, null);
		System.out.println(items);
		
	}
	
	public void test1()
	{
		ReportServerImpl server = new ReportServerImpl();
		List<Column> columns=new ArrayList<Column>();
		columns.add(new Column("Key1", "Key1", "", false,0,2));
		columns.add(new Column("Key2", "Key2", "", true,0,1));
		columns.add( new Column("Value1", "Value1", "Sum", false,-1,0));
		List<Filter> filters=new ArrayList<Filter>();
		filters.add(new Filter("Key1","EqualsCondition","a",""));
		List<Filter> groupFilters=new ArrayList<Filter>();
		groupFilters.add(new Filter("Key1","EqualsCondition","a",""));
		Query query = new Query("DomesticPRE",null, "DomesticFares",columns,filters,groupFilters);
		@SuppressWarnings("unchecked")
		List<ReportData> rootGroup = (List<ReportData>) server.generateReport(query,-1, null);
		System.out.println(rootGroup);
		for(ReportData reportData: rootGroup)
		{
			List<ReportData> items = server.expandGroup(1, 1, reportData.getIndexes(),null).getRows();
			System.out.println(items);
			for(ReportData myReportData: items)
			{
				List<ReportData> items1 = server.expandGroup(1, 2, myReportData.getIndexes(),null).getRows();
				System.out.println(items1);
			}
		}
		
	}
}
