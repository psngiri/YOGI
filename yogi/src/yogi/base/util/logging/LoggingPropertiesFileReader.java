package yogi.base.util.logging;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.Reader;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;

public class LoggingPropertiesFileReader implements Reader<Object> {
	public static final String OutputDirectoryLocation = "%o";
	private static Logger logger = Logging.getLogger(LoggingPropertiesFileReader.class);
	public static boolean RUN = true;
	public static String FileName = "*/config/logging.properties";
	
	public List<Object> read() {
		SystemResource resource = ResourceAssistant.getResource(FileName);
		if (!resource.canRead()) {
			throw new RuntimeException("Could not read from " + resource);
		}
		logger.info("Reading from " + resource);
		InputStream inputStream = insertOutputDirectory(resource.getReader());
		try {
			LogManager.getLogManager().readConfiguration(inputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	@SuppressWarnings("deprecation")
	private InputStream insertOutputDirectory(java.io.Reader reader)
	{
		LineNumberReader lineNumberReader = new LineNumberReader(reader);
		String outputLocation = ApplicationProperties.OutputLocation;
		outputLocation = outputLocation.replace("\\", "/");
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			String line = null;
				while((line = lineNumberReader.readLine()) != null)
				{
					if(line.contains(OutputDirectoryLocation))
					{
						if(outputLocation.trim().length() == 0) continue;
						makeSureDirectoryExistsCreateIfNeeded(line);
						line = line.replace(OutputDirectoryLocation, outputLocation);
						if(logger.isLoggable(Level.INFO)) logger.info(String.format("Replaced %1$s with %2$s in line: %3$s", OutputDirectoryLocation, ApplicationProperties.OutputLocation, line));
					}
					pw.println(line);
				}
				pw.close();
			return new java.io.StringBufferInputStream(sw.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void makeSureDirectoryExistsCreateIfNeeded(String line) {
		int indexOf = line.indexOf(OutputDirectoryLocation);
		String inputDirectory = line.substring(indexOf, line.length()-1);
		String outputDirectory = inputDirectory.replace(OutputDirectoryLocation, ApplicationProperties.OutputLocation);
		File file = new File(outputDirectory);
		file.getParentFile().mkdirs();
	}
	
	public boolean isActivated() {
		return RUN;
	}

}
