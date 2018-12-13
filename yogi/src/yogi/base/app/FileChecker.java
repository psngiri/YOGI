package yogi.base.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.property.PropertyReplacer;

public class FileChecker implements Checker{
	private List<File> files;
	private PropertyReplacer propertyReplacer  = new PropertyReplacer();
	private String[] fileNames;;
	public FileChecker(String... files)
	{
		this.fileNames = files;
	}
	
	public FileChecker(File... files)
	{
		this.files = Arrays.asList(files);
	}
	
	@Override
	public boolean check() {
		for(File file:getFiles())
		{
			if(!file.exists())  return false;
			
		}
		return true;
	}

	private List<File> getFiles() {
		if(files ==  null && fileNames != null){
			this.files= new ArrayList<File>(fileNames.length);
			for(String file:fileNames)
			{
				this.getFiles().add(new File(propertyReplacer.replaceVariables(file)));
			}
		}
		return files;
	}

}
