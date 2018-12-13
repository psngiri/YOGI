package yogi.client.app;

import java.util.Scanner;

import yogi.base.io.FileRecordReader;

public class GsonCommandApplication {
	public static void main(String[] args) {
		if(args.length < 4) {
			System.out.println("Usage: GsonCommandApplication  server serverType GsonCommandLogFile [commandOutputDirectoryName]");
			return;
		}
		String server = args[0];
		String serverType = args[1];
		String gsonCommandLogFile = args[2];
		String commandOutputDirectoryName = ".";
		if(args.length > 3) commandOutputDirectoryName = args[3];
		GsonCommandApplication gsonCommandApplication = new GsonCommandApplication();
		gsonCommandApplication.execute(server, serverType, gsonCommandLogFile, commandOutputDirectoryName);
	}

	private void execute(String server, String serverType, String gsonCommandLogFile, String commandOutputDirectoryName) {
		FileRecordReader fileRecordReader = new FileRecordReader(gsonCommandLogFile);
		fileRecordReader.open();
		try {
			CommandApplication commandApplication = new CommandApplication();
			int i = 1;
			while(fileRecordReader.hasNext())
			{
				String next = fileRecordReader.next();
				Scanner scanner = new Scanner(next);
				String key = null;
				String value = null;
				String myServerType = null;
				while(scanner.hasNext()) {
					String token = scanner.next();									 
					if(key  == null && token.startsWith("Key")) {
						String myValue = token.split("=")[1];
						key = myValue.substring(0, myValue.length()-1);
					}else if(value  == null && token.startsWith("Value")) {
						value = token.split("=")[1];
					}else if(myServerType  == null && token.startsWith("ServerType")) {
						myServerType = token.split("=")[1];
					}
					if(key != null && value != null && myServerType != null) break;
				}
				int valueIndex = next.indexOf("Value=");
				if(valueIndex != -1) value = next.substring(valueIndex + 6);
				//value = value.replaceAll("%", "%25");
				scanner.close();
				if(myServerType == null) myServerType = serverType;
				if(key != null && value != null && myServerType != null) {
					commandApplication.executeCommand(server, myServerType, key, value, commandOutputDirectoryName + "/" + (i++ + ".out"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fileRecordReader.close();
		}
	}

}
