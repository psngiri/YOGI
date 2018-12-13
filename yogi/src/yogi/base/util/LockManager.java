package yogi.base.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class LockManager implements Runnable {
	public static boolean Disable = false;
	File directoryToCreateTheLockFileIn;
	String name;
	String userName;
	File lockFile;
	Runnable runnable;
	
	public LockManager(String directoryToCreateTheLockFileIn, String name) {
		this(directoryToCreateTheLockFileIn, name, null);
	}
	
	public LockManager(String directoryToCreateTheLockFileIn, String name, Runnable runnable) {
		super();
		this.directoryToCreateTheLockFileIn = getDirectory(directoryToCreateTheLockFileIn);
		this.name = name;
		userName = System.getProperty("user.name");
		this.runnable = runnable;
	}
	private File getDirectory(String directoryToCreateTheLockFileIn) {
		File file = new File(directoryToCreateTheLockFileIn);
		if(!file.exists())
		{
			file.mkdirs();
		}
		return file;
	}
	
	public File getLockFile() {
		return lockFile;
	}
	public void checkAndCreateLock()
	{
		if(Disable) return; 
		File[] files = directoryToCreateTheLockFileIn.listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				return pathname.getName().startsWith(name);
			}
			
		});
		if(files.length > 0) throw new RuntimeException("Lock File Exists: "+ files[0].getAbsolutePath());
		lockFile = new File(directoryToCreateTheLockFileIn, String.format("%s.%s.lock", name, userName));
		try {
			if(!lockFile.createNewFile()) throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException("Could not create the Lock file: " + lockFile.getAbsolutePath(), e);
		}
	}
	
	public void removeLock()
	{
		if(Disable) return;
		if(!lockFile.delete()) throw new RuntimeException("Could not delete the Lock file: " + lockFile.getAbsolutePath());
	}
	
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	public void run() {
		if(Disable) return;
		if(runnable == null) throw new RuntimeException("Runnable not set");
		try {
			this.checkAndCreateLock();
			runnable.run();
		} finally{
			this.removeLock();
		}
	}
}
