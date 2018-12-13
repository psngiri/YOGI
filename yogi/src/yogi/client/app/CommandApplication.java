package yogi.client.app;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import yogi.base.io.FileToStringReader;
import yogi.base.io.StringToFileWriter;
import yogi.base.io.resource.FileResource;
import yogi.base.net.URLConnection;



public class CommandApplication {

	public static void main(String[] args) {
		if(args.length < 4) {
			System.out.println("Usage: CommandApplication  server serverType commandName commandJsonFileName [commandOutputJsonFileName]");
			return;
		}
		String server = args[0];
		String serverType = args[1];
		String commandName = args[2];
		String commandJsonFileName = args[3];
		String commandOutputJsonFileName = null;
		if(args.length > 4) commandOutputJsonFileName = args[4];
		CommandApplication commandApplication = new CommandApplication();
		commandApplication.execute(server, serverType, commandName, commandJsonFileName, commandOutputJsonFileName);
	}

	public void execute(String server, String serverType, String commandName, String commandJsonFileName, String commandOutputJsonFileName) {
		FileToStringReader fileToStringReader = new FileToStringReader(new FileResource(commandJsonFileName), 0);
		String value = fileToStringReader.read();
		executeCommand(server, serverType, commandName, value, commandOutputJsonFileName);
	}

	public void executeCommand(String server, String serverType, String commandName, String value, String commandOutputJsonFileName) {
		try {
			String url = String.format("http://%s/JAFWebApp/CommandServlet?ServerType=%s&Key=%s&Value=%s", server, serverType, commandName, value);
			URLConnection urlConn = new URLConnection(url, true);
			byte[] bytes = urlConn.response().toString().getBytes();             
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				if(commandOutputJsonFileName != null){
					StringToFileWriter stringToFileWriter = new StringToFileWriter(commandOutputJsonFileName);
					stringToFileWriter.write(inputLine);
					System.out.println(String.format("%s successfully processed and output written to %s", commandName, commandOutputJsonFileName));
				}else{
					System.out.println(inputLine);
				}
			in.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

