package yogi.report.server.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import yogi.base.Util;
import yogi.base.app.ApplicationProperties;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.QueryFile;
import yogi.report.server.UserQuery;
import com.thoughtworks.xstream.XStream;

public class UserQueryAssistant {
	
	public static String ReportQueriesLocation = "/ReportQueries/";
	private static UserQueryAssistant itsInstance = null;
	private XStream xstream = new XStream();
	
	
	private UserQueryAssistant(){
		 xstream.alias("Query", Query.class);
		 xstream.alias("Column", Column.class);
		 xstream.alias("Filter", Filter.class);
	}
	
	public static UserQueryAssistant get() {
		if(itsInstance == null){
			itsInstance = new UserQueryAssistant();
		}
		return itsInstance;
	}
	public  boolean saveQuery(UserQuery userQuery) {
		File file = new File(getReportQueriesLocation()+"/"+userQuery.getUserName()+"/"+userQuery.getQueryName()+".xml");
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(file);
			 FileWriter fstream;
			try {
				 fstream = new FileWriter(file);
				 BufferedWriter out = new BufferedWriter(fstream);
				 out.write(xstream.toXML(userQuery.getQuery()));
				 out.close();
				 return true;
			  } catch (IOException e) {
					e.printStackTrace();
			}
		return false;
	}
	public String getReportQueriesLocation() {
		return ApplicationProperties.OutputLocation+ReportQueriesLocation;
	}
	
	public List<QueryFile> getQueries(String userName)
	{
		List<QueryFile> userQueries = new ArrayList<QueryFile>();
		final File folder = new File(getReportQueriesLocation()+userName);
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(folder);
		File[] listFiles = folder.listFiles();
		Arrays.sort(listFiles, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					
					return (int) (o2.lastModified()-o1.lastModified());
				}
         	
         });
         for (File fileEntry : listFiles) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				userQueries.add(new QueryFile(fileEntry.getName().replaceFirst("[.][^.]+$", ""),sdf.format(fileEntry.lastModified())));
		    }
		
		return userQueries;
	}
	

	public List<String> getQueryUsers()
	{
		List<String> userNames = new ArrayList<String>();
		final File folder = new File(getReportQueriesLocation());
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(folder);
		for (File fileEntry : folder.listFiles()) {
				userNames.add(fileEntry.getName());
		    }
		
		return userNames;
		
	}
	
	public Query getQuery(String userName,String queryName) {
		try {
			File file = new File(getReportQueriesLocation()+userName+"/"+queryName+".xml");
			Util.checkAndCreateFileAlongWithParentDirsAsRequired(file);
		    BufferedReader in = new BufferedReader(new FileReader(file));
		    Query query=(Query)xstream.fromXML(in);
		    in.close();
		    return query;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void getUserInfo(String userId){
		
	}
	
}