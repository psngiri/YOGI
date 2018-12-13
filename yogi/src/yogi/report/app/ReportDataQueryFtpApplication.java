package yogi.report.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;



public class ReportDataQueryFtpApplication {
	public static void main(String[] args) {
		if(args.length < 5) {
			System.out.println("Usage: ReportDataApplication  serverType server UserId QueryName FtpLocation ReportDataFileName [Partition, columnSeparator]");
			return;
		}
		String url;
		if(args.length == 8){
			url = String.format("http://%s/JAFWebApp/CommandServlet?ServerType=%s&Key=yogi.server.gui.report.command.GenerateReportAndFtpCommand&Value={\"name\":\"%s\",\"userId\":\"%s\",\"ftpLocation\":\"%s\",\"reportDataFileName\":\"%s\",\"partition\":\"%s,\"columnSeparator\":\"%s\"}",args[1], args[0], args[3], args[2], args[4], args[5], args[6], args[7]);
		}else if(args.length == 7){
			url = String.format("http://%s/JAFWebApp/CommandServlet?ServerType=%s&Key=yogi.server.gui.report.command.GenerateReportAndFtpCommand&Value={\"name\":\"%s\",\"userId\":\"%s\",\"ftpLocation\":\"%s\",\"reportDataFileName\":\"%s\",\"partition\":\"%s\"}",args[1], args[0], args[3], args[2], args[4], args[5], args[6]);
		}else{
			url = String.format("http://%s/JAFWebApp/CommandServlet?ServerType=%s&Key=yogi.server.gui.report.command.GenerateReportAndFtpCommand&Value={\"name\":\"%s\",\"userId\":\"%s\",\"ftpLocation\":\"%s\",\"reportDataFileName\":\"%s\"}",args[1], args[0], args[3], args[2], args[4], args[5]);
		}
		try {
			URL myUrl = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader( myUrl.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				if(inputLine.trim().equals("true")){
					System.out.println(String.format("Report generated and ftped to %s %s", args[3], args[4]));
				}else{
					System.out.println(inputLine);
				}
			in.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

