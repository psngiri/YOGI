package yogi.base.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseHeaderFileWriter extends BaseFileWriter{
	private List<String> header = new ArrayList<String>();

	protected BaseHeaderFileWriter()
	{
		super();
	}
	
	public BaseHeaderFileWriter(String fileName) {
		super(fileName);
	}
	
	public BaseHeaderFileWriter(File file) {
		super(file);
	}

	public boolean open() {
		boolean returnValue = super.open();
		if(returnValue) writeHeader();
		return returnValue;
	}

	private void writeHeader() {
		for(String headerRecord: getHeader())
		{
			writer.println(headerRecord);
		}
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}
	
	public void addHeader(String headerRecord)
	{
		header.add(headerRecord);
	}

	public List<String> getHeader()
	{
		return header;
	}

}
