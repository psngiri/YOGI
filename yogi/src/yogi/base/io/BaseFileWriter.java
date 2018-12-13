package yogi.base.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.Util;
import yogi.base.io.resource.FileResource;
import yogi.base.util.logging.Logging;

public class BaseFileWriter {
	private static Logger logger = Logging.getLogger(BaseFileWriter.class);
	private FileResource fileResource;
	protected PrintWriter writer;
	private boolean append = false;

	protected BaseFileWriter()
	{
		
	}
	
	public BaseFileWriter(String fileName) {
		super();
		this.fileResource = new FileResource(fileName);
	}
	
	public BaseFileWriter(File file) {
		super();
		this.fileResource = new FileResource(file);
	}

	public File getFile() {
		return fileResource.getFile();
	}

	public boolean close() {
		writer.close();
		return true;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public boolean open() {
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(getFile());
		try {
			writer = new PrintWriter(new BufferedWriter(new java.io.FileWriter(
					getFile(), isAppend())));
			 if(logger.isLoggable(Level.INFO))
			 {
				 logger.info("Writing to file " + getFile().getAbsolutePath());
			 }
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}
