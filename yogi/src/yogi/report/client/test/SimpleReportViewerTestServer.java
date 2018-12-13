package yogi.report.client.test;

import java.util.ArrayList;
import java.util.List;

import yogi.report.client.ReportViewer;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;

public class SimpleReportViewerTestServer {
	
	public static void main(String[] args) {
		
		List<Column> columns=new ArrayList<Column>();
		columns.add(new Column("Key1", "Key1", "", false,0,0));
		columns.add(new Column("Key2", "Key2", "", false,0,0));
		columns.add(new Column("Key3", "Key3", "", false,0,0));
		columns.add(new Column("Value1", "Value1", "Sum", false,0,0));
		columns.add(new Column("Value2", "Value2", "Sum", false,0,0));
		List<Filter> filters=new ArrayList<Filter>();
		filters.add(new Filter("Key2","<","b1","alias"));
		List<Filter> groupFilters=new ArrayList<Filter>();
		groupFilters.add(new Filter("Value1","<=","40",""));
		Query query = new Query( "DomesticPRE",new String[]{"1"},"DomesticFares",columns,filters,groupFilters);
		
		new ReportViewer(query, null ,"Domestic");
    }

}

