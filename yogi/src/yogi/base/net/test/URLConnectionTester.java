package yogi.base.net.test;

import junit.framework.TestCase;

import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.net.URLConnection;
import yogi.base.util.JsonAssistant;

public class URLConnectionTester extends TestCase {
	private static String hostServer = "rmprid01.tul.aa.com:5089";
	
	public void testGet() throws Exception {
		String commandName = "yogi.report.server.command.GetApplicationsCommand";
		String valueString = "{\"userId\":\"AA935213\"}";
		URLConnection urlConnection = new URLConnection("http://"+hostServer+"/JAFWebApp/CommandServlet?ServerType=Strategy&Key=" + commandName + "&Value=" + valueString, false);
		StringBuffer response = urlConnection.response();
		System.out.println(response);
	}
	
	public void testPost() throws Exception {
		String commandName = "yogi.report.server.command.GetApplicationsCommand";
		String valueString = "{\"userId\":\"AA935213\"}";
		URLConnection urlConnection = new URLConnection("http://"+hostServer+"/JAFWebApp/CommandServlet?ServerType=Strategy&Key=" + commandName + "&Value=" + valueString, true);
		StringBuffer response = urlConnection.response();
		System.out.println(response);
	}
	
	public void testGetJson() throws Exception {
		String commandName = "com.aa.rm.pricing.strategy.command.RecommendationSaveAndTransmitCommand";
		SystemResource resource = new ClassPathResource("jsonData.txt", this.getClass());
		Object objStr = JsonAssistant.get().fromJsonFile(resource , 0, Object.class);
		String jsonString = JsonAssistant.get().toJson(objStr);		
		URLConnection urlConnection = new URLConnection("http://"+hostServer+"/JAFWebApp/CommandServlet?ServerType=DomesticPRE&Key=" + commandName + "&Value=" + jsonString, false);
		StringBuffer response = urlConnection.response();
		System.out.println(response);
	}
	
	public void testPostJson() throws Exception {
		String commandName = "com.aa.rm.pricing.strategy.command.RecommendationSaveAndTransmitCommand";
		SystemResource resource = new ClassPathResource("jsonData.txt", this.getClass());
		Object objStr = JsonAssistant.get().fromJsonFile(resource , 0, Object.class);
		String jsonString = JsonAssistant.get().toJson(objStr);		
		URLConnection urlConnection = new URLConnection("http://"+hostServer +"/JAFWebApp/CommandServlet?ServerType=DomesticPRE&Key=" + commandName + "&Value=" + jsonString, true);
		StringBuffer response = urlConnection.response();
		System.out.println(response);
	}
}
