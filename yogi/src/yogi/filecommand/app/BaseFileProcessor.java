package yogi.filecommand.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import yogi.base.app.ErrorReporter;
import yogi.base.app.multithread.Callable;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.io.SimpleDateFormatter;
import yogi.period.date.io.YYMMDDDateFormatter;

public abstract class BaseFileProcessor implements Callable<File>{
	public static String BackupDir="";
	public static String Suffix = "";
	public static boolean DontChangeFileName=false;
	public static boolean DeleteDoneFile=true;
	public static int waitTime = 40000;//in milliseconds
	static SimpleDateFormatter simpleFormatter = new SimpleDateFormatter(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
	static YYMMDDDateFormatter dateFormatter = new YYMMDDDateFormatter();
	static HashMap<String,Long> map = new HashMap<String,Long>();

	public BaseFileProcessor() {
		super();
	}
	
	@Override
	public void call(File doneFile) {
			String doneFileAbsolutePath = doneFile.getAbsolutePath();
			int lastIndexOf = doneFileAbsolutePath.lastIndexOf(getSuffix());
			if(!getSuffix().trim().isEmpty()){
				process(new File(doneFileAbsolutePath.substring(0, lastIndexOf)),doneFile);
			}else{
				process(doneFile,doneFile);
			}
	}
	
	private void process(File file, File doneFile) {
		Date date = DateAssistant.get().getDate(new java.util.Date());
		long lastModified = getLastModifiedTimeForTheFileAfterItIsComplete(file, doneFile);
		ErrorReporter.get().finest("Start Processing File : " +file.getName()+" at "+simpleFormatter.format(date));
		executeFile(file);
		File backupDir = getBackupDir(file);
		String fileName = file.getName() + dateFormatter.format(date);
		if(isDontChangeFileName()){
			fileName = file.getName();
		}
		moveToBackUp(file, backupDir, fileName);//backing up actual file if BackupDir String is not empty
		if (file != doneFile) {//that means suffix is not empty
			if (doneFile.exists()) {
				if (backupDir == null || isDeleteDoneFile()) {
					doneFile.delete();//Removing done file
				} else {
					moveToBackUp(doneFile, backupDir, doneFile.getName());//backing up done file if BackupDir String is not empty
				}
			}
		}else{
			getMap().put(file.getName(), lastModified);
		}
		ErrorReporter.get().finest("Finish Processing File : " +file.getName()+" at "+simpleFormatter.format(date));
	}

	protected abstract void executeFile(File file);

	private long getLastModifiedTimeForTheFileAfterItIsComplete(File file, File doneFile) {
		long lastModified = file.lastModified();
		if(doneFile == file)//that means suffix is empty
		{
			ErrorReporter.get().finest("Waiting for the file "+ file.getName() +" to Finish");
			long time = 0;
			while(time != lastModified)//waiting until file is complete
			{
				time = lastModified;
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lastModified = file.lastModified();
			}
		}
		return lastModified;
	}
	
	private void moveToBackUp(File file, File backUpDirectory, String fileName){
		if (backUpDirectory != null) {
			boolean success = file.renameTo(new File(getBackupDir(), fileName));
			if (!success) {
			    ErrorReporter.get().warning("Unable to Move following Files to Back Up Directory ", file.getAbsolutePath());
		    }
		}
	}
	
	public File getBackupDir(File file) {
		if(getBackupDir().trim().isEmpty()) return null;
		File bakDir = new File(getBackupDir());
		if (bakDir.exists()) return bakDir;
		synchronized (this) {
			if (!bakDir.exists()) {//creates the backUp directory if doesnot exist
				boolean success = bakDir.mkdir();
				if (!success) {
					ErrorReporter.get().warning("Unable to create Back Up Directory ",bakDir.getAbsolutePath());
				}
			}
		}
		return bakDir;
	}
	
	public boolean open() {
		return true;
	}

	public boolean close() {
		return true;
	}
	protected  HashMap<String, Long> getMap() {
		return map;
	}

	protected String getSuffix()
	{
		return Suffix.trim().isEmpty() ? Suffix : "."+Suffix.trim();
	}
	
	protected String getBackupDir() {
		return BackupDir;
	}

	protected boolean isDontChangeFileName() {
		return DontChangeFileName;
	}

	protected boolean isDeleteDoneFile() {
		return DeleteDoneFile;
	}

}