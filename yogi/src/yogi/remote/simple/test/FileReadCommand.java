package yogi.remote.simple.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.remote.simple.Command;


public class FileReadCommand implements Command {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983426677596434214L;
	private static Logger logger = Logger.getLogger(FileReadCommand.class.getName());
	private String fileName;
	private int offset;
	private String id;
	
	public FileReadCommand(String fileName, int offset, String id) {
		super();
		this.fileName = fileName;
		this.offset = offset;
		this.id = id;
	}
	
	private String getResourceAsSubstring(String fileName, int offset) throws IOException {
		if(fileName == null)
			return null;
		File logFile = new File(fileName);
        String logText = null;
        int newTextLength = (int)logFile.length() - offset; 
        if(newTextLength > 0){		    
		    char[] charBuffer = new char[newTextLength];
		    FileReader fReader = new FileReader(fileName);
		    fReader.skip(offset);
		    fReader.read(charBuffer);
		    fReader.close();
		    logText = new String(charBuffer);
        }
        return logText;
	}

	public String execute() {
		logger.log(Level.INFO, "Reading File " + fileName + " from offset" + offset);
			try {
				return getResourceAsSubstring(fileName, offset);
			} catch (Throwable e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

	public String getID() {
		return id;
	}


}
