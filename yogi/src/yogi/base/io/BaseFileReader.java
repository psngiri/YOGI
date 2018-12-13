package yogi.base.io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public abstract class BaseFileReader {
	private static Logger logger = Logging.getLogger(BaseFileReader.class);
	private SystemResource resource;
	protected LineNumberReader reader;
	private int headerLineCount = 0;
	private List<String> header = new ArrayList<String>();

	public BaseFileReader(String fileName, int headerLineCount)
	{
		this(ResourceAssistant.getResource(fileName), headerLineCount);
	}
	
	public BaseFileReader(SystemResource resource, int headerLineCount)
	{
		super();
		this.resource = resource;
		this.headerLineCount = headerLineCount;
	}

	public SystemResource getResource() {
		return resource;
	}

	public boolean open(){
		if (!resource.canRead()) {
			throw new RuntimeException("Could not read from " + resource);
		}
		 reader = new LineNumberReader(
				 resource.getReader());
		 if(logger.isLoggable(Level.INFO))
		 {
			 logger.info("Reading from " + resource);
		 }
		return readHeader();
	}
	
	private boolean readHeader() {
		try {
			for(int i = 0; i < headerLineCount; i ++)
			{
				String record;
				if((record = reader.readLine()) == null) 
				{
					throw new RuntimeException("Expected "+ headerLineCount + " headers lines in " + resource);
				}
				header.add(record);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error in " + resource, e);
		}
		return true;
	}

	public boolean close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Error in " + resource, e);
		}
		return true;
	}

	
	public List<String> getHeader() {
		return header;
	}

	public int getHeaderLineCount() {
		return headerLineCount;
	}

}
