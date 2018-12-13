package yogi.report.server.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.report.server.Column;
import yogi.report.server.Query;
import yogi.report.server.QueryFile;
import yogi.report.server.UserQuery;
import yogi.report.server.config.UserQueryAssistant;

public class UserQueryAssistantTestServer extends TestCase {

	public void test()
	{
		List<Column> columns= new ArrayList<Column>();
		columns.add(new Column("Origin", "Origin", "", true, 0, 0));
		Query query=new Query("DomesticPRE",null,"DomesticFares",columns,null,null);
		UserQuery userQuery=new UserQuery("Kishore1","AOriginQuery3",query);
		Boolean rtnValue=UserQueryAssistant.get().saveQuery(userQuery);
		assertEquals("SUCCESS", rtnValue);
		assertEquals(query.toString(), UserQueryAssistant.get().getQuery("Kishore1", "AOriginQuery3").toString());
		List<String> queryUsers = UserQueryAssistant.get().getQueryUsers();
		System.out.println(queryUsers.size());
		List<QueryFile> queriesList = UserQueryAssistant.get().getQueries("Kishore1");
		for(QueryFile f:queriesList){
			System.out.println(f.getQueryName()+" "+f.getTimeStamp());
		}
		
	}
	
}
