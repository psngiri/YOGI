package yogi.remote.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;

public class FileReadCommand implements Command<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983426677596434214L;
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
			ErrorReporter.get().log(Level.INFO, "Reading File " + fileName + " from offset" + offset);
			try {
				return getResourceAsSubstring(fileName, offset);
			} catch (Throwable e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

	public String getID() {
		return id;
	}

	@Override
	public String getUserId() {
		return null;
	}


}
