package yogi.optimize.remote.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Util {

	public static StringBuffer getFileIntoStringBuffer(String fileName) throws IOException {
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		try {
			int ch = -1;
			while( ( ch = fileReader.read() ) != -1 ) {
				sb.append((char)ch);
			}
		} 
		finally {
			if ( fileReader != null ) {
				fileReader.close();
			}
		}
		return sb;
	}
	
	public static void createFileFromStringBuffer(File file, StringBuffer fileContent) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		try {
			fileWriter.write(fileContent.toString());
		}
		finally {
			if ( fileWriter != null ) {
				fileWriter.close();
			}
		}
	}
	
	public static String checkAndCreateHomeDirectory(String homeDirectory) {
		if ( homeDirectory == null ) {
			homeDirectory = CplexJavaConnectServer.ROOT_DIRECTORY + "/" + System.getProperty("user.name") + "/" + CplexJavaConnectServer.SERVER_LOOKUP_NAME;
		}
		checkAndCreateDirectory(homeDirectory);
		return homeDirectory;
	}
	
	public static String createTempDirectory(String homeDirectory) {
		String tempDirectoryName = homeDirectory + "/" + Long.toString(Calendar.getInstance().getTimeInMillis());
		checkAndCreateDirectory(tempDirectoryName);
		return tempDirectoryName;
	}
	
	public static void checkAndCreateDirectory(String directory) {
		File directoryFile = new File(directory);
		if ( !directoryFile.exists() ) {
			directoryFile.mkdirs();
		}
	}
}
