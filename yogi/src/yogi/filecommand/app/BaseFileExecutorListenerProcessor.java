package yogi.filecommand.app;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import yogi.base.app.BaseProcessor;

public abstract class BaseFileExecutorListenerProcessor extends BaseProcessor{
	
	public static String InputDirectoryPath="";
	public static String RegularExpresion=".*";
	public BaseFileExecutorListenerProcessor() {
		super();
	}
	
	public abstract BaseFileProcessor getFileProcessor();

	public String getDirectoryPath() {
		return InputDirectoryPath;
	}

	@Override
	public void run() {
		File dir = new File(getInputDirectoryPath()); 
		
		File[] files = dir.listFiles(getFileFilter());
		
		if(files == null || (files!=null && files.length==0)) return;
	        		
		List<File> sortedFiles = getSortedList(files);
		if(getSuffix().trim().isEmpty()){
			List<File> toProcessfiles = new ArrayList<File>();
			for(File file : sortedFiles){
				long prevModifiedTime = getMap().get(file.getName()) == null ? 0 : getMap().get(file.getName());
				if(prevModifiedTime == file.lastModified()) continue;
				toProcessfiles.add(file);
			}
			if(toProcessfiles.isEmpty()) return;
			processFiles(toProcessfiles);
		}else{
			processFiles(sortedFiles);
		}
	}

	protected void processFiles(List<File> files) {
		for(File file:files)
		{
			getFileProcessor().call(file);
		}
	}

	protected FilenameFilter getFileFilter(){	
		return new FilenameFilter() 
			{//Gets the list of files in the directory matching the given pattern
				@Override
				public boolean accept(File file, String name) {
					File file1 = new File(file.getAbsolutePath()+"/"+name);
					if(file1.isDirectory()) return false;
					return name.matches(getRegex().trim()+getRegExSuffix());
				}
			};
	}

	private  String getRegExSuffix(){
		String mySuffix = getSuffix();
		return mySuffix.trim().isEmpty() ? mySuffix : "\\"+mySuffix.trim();
	}
	
	protected HashMap<String, Long> getMap() {
		return getFileProcessor().getMap();
	}

	protected String getSuffix()
	{
		return getFileProcessor().getSuffix();
	}
	protected String getInputDirectoryPath() {
		return InputDirectoryPath;
	}

	protected String getRegex(){
		return RegularExpresion;
	}
	
	private List<File> getSortedList(File[] files) {
		Arrays.sort(files, new Comparator<File>()
		{
			public int compare(File o1, File o2) {
				if (o1.lastModified() > o2.lastModified()) {
					return -1;
				} else if (o1.lastModified() < o2.lastModified()) {
					return +1;
				} else {
					return 0;
				}
			}
		});
		return Arrays.asList(files);
	}
	
}
		
	

