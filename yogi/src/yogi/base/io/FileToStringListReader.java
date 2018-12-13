package yogi.base.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yogi.base.io.resource.SystemResource;

public class FileToStringListReader extends BaseFileReader {

	public FileToStringListReader(String fileName, int headerLineCount) {
		super(fileName, headerLineCount);
	}
	
	public FileToStringListReader(SystemResource resource, int headerLineCount) {
		super(resource, headerLineCount);
	}

	public List<String> read()
	{
		boolean success = open();
		if(!success) return null;
		List<String> rtnValue = new ArrayList<String>();
		String line = null;
		try {
			while((line = reader.readLine()) != null)
			{
				rtnValue.add(adjust(line.trim()));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		close();
		return rtnValue;
	}

	protected String adjust(String line) {
		return line;
	}
}
