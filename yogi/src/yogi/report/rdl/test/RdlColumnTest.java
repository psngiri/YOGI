package yogi.report.rdl.test;

import java.util.ArrayList;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.report.rdl.RdlColumn;
import yogi.report.rdl.RdlColumns;
import yogi.report.rdl.RdlType;

import junit.framework.TestCase;

public class RdlColumnTest  extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
		
	}
	
	public void test()
	{
		RdlColumns columns = new RdlColumns();
		columns.add(new RdlColumn(RdlType.STRING, "A", 3));
		columns.add(new RdlColumn(RdlType.INTEGER, "B", 4));
		columns.add(new RdlColumn(RdlType.FLOAT, "C", 4));
		columns.add(new RdlColumn(RdlType.TIME,"D", 4));
		String json = JsonAssistant.get().toJson(columns);
		System.out.println(json);
		RdlColumns fromJson = JsonAssistant.get().fromJson(json, columns.getClass());
		System.out.println(fromJson);
		
	}
	
	
}
