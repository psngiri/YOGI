package yogi.mapreduce.reducer.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.mapreduce.reducer.ListGsonReducer;


public class ListGsonReducerTest extends TestCase{

	public void test()
	{
		List<String> results = new ArrayList<String>();
		results.add("[{\"action\":2,\"timeStamp\":1444425101608,\"modifiedByUserId\":\"test\",\"market\":\"DFWSFO\",\"attribute\":\"testAttribute2\"}]");
		results.add("[{\"action\":2,\"timeStamp\":1444425072445,\"modifiedByUserId\":\"test\",\"market\":\"DFWJFK\",\"attribute\":\"testAttribute1\"}]");
		ListGsonReducer listGsonReducer = new ListGsonReducer();
		String result = listGsonReducer.reduce(results);
		System.out.println(result);		
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		super.tearDown();
	}
}
