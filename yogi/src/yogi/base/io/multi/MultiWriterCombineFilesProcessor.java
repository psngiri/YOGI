package yogi.base.io.multi;

import yogi.base.app.RemoteCommandProcessor;
import yogi.base.app.multithread.ThreadPoolExecutor;


public abstract class MultiWriterCombineFilesProcessor extends RemoteCommandProcessor {
	private String fileName;
	public static String Script = "combineFiles.sh";
	
	public MultiWriterCombineFilesProcessor(String fileName) {
		super();	
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		if(ThreadPoolExecutor.NumberOfThreads == 1) return;
		this.setCommand(getScriptsDirectory()+"/"+getScript(), getFileName(), getDirectory());
		super.run();
	}

	public abstract String getScriptsDirectory();

	private String getFileName() {
		return fileName;
	}

	public String getScript(){
		return Script;
	}
	
	public abstract String getDirectory();
}
