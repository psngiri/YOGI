package yogi.base.io;

import java.io.IOException;
import java.util.logging.Logger;

import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public class FileToStringReader extends BaseFileReader {
	private static Logger logger = Logging.getLogger(FileToStringReader.class);

	public FileToStringReader(SystemResource resource, int headerLineCount) {
		super(resource, headerLineCount);
	}
	
	public String read()
	{
		boolean success = open();
		if(!success) return null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null)
			{
				sb.append(adjust(line));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		close();
		String rtnValue = sb.toString();
		logger.fine(rtnValue);
		return rtnValue;
	}

	protected String adjust(String line) {
		return line;
	}
}
