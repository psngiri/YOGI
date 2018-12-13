package yogi.report.app.client;

import yogi.base.net.URLConnection;
import com.google.gson.Gson;



public class ExportReportClientApplication {
	public static String ServerIpPortNumber = "localhost:8080";

	public static void main(String[] args) {
		if(args.length < 4) {
			System.out.println("Usage: ReportClientApplication  ServerIpPortNumber UserId Partition QueryName ForecastNames [filterValue ...]");
			System.out.println("Example: ReportClientApplication  localhost:8080 AA627674 D Query1 \"forecast1,forecast2...\" \"ATLDFW,CHIDFW\" \"DL\"");
			return;
		}
		ExportReportClientApplication.ServerIpPortNumber = args[0];
		
		int fiterValuesSize = args.length-5;
		String[] filterValues=new String[fiterValuesSize];
		for(int i = 0; i < fiterValuesSize ; i++){
			filterValues[i] = args[4+i];
		}
		ReportClientQuery riQuery=new ReportClientQuery(args[3], args[2], filterValues, args[4].split(","), args[1]);
		String[][] data = executeQuery(riQuery);
		for(String[] row: data)
		{
			for(int i = 0; i < row.length -1; i ++){
				System.out.print(row[i]);
				System.out.print(',');
			}
			System.out.println(row[row.length-1]);
		}
	}

	public static String[][] executeQuery(ReportClientQuery riQuery) {
		String url;
		Gson gson = new Gson();
		String json = gson.toJson(riQuery);
		url = String.format("http://%s/JAFWebApp/CommandServlet?ServerType=Strategy&Key=yogi.server.gui.report.command.ExportGenerateReportServiceCommand&Value="+json,ServerIpPortNumber);		
		try {
			URLConnection myUrl = new URLConnection(url, true);

			StringBuffer response = myUrl.response();
			
			String[][] data = new String[0][0];
			Class<? extends String[][]> class1 = data.getClass();
			if(response.substring(2).startsWith("ErrorMessage")) throw new RuntimeException(response.toString());
			data = gson.fromJson(response.toString(), class1);
			return data;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}

