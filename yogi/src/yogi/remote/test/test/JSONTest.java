package yogi.remote.test.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
	}

	public void test()
	{
		List<ReportName> names = new ArrayList<ReportName>();
		names.add(new ReportName("Test"));
		names.add(new ReportName("Domesting"));
		Gson gson = new Gson();
		String json = gson.toJson(names);
		System.out.println("toJson:\n"+json);
		Type listType = new TypeToken<ArrayList<ReportName>>() {}.getType();
		List<ReportName> fromJson = gson.fromJson(json, listType);
		ReportName reportName = fromJson.get(0);
		System.out.println("\nfromJson\n"+reportName);
		//assertNotNull(fromJson);
	}
}
