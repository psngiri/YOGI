package yogi.base.io.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;

import yogi.property.PropertyReplacer;

public class FileResource implements SystemResource {
	protected String fileName;
	protected File file;

	public FileResource(String fileName) {
		this.fileName = fileName;
	}

	public FileResource(File file) {
		this.file = file;
	}

	public File getFile() {
		if(file == null) file = new File(locallySlashedPath(new PropertyReplacer().replaceVariables(fileName)));
		return file;
	}
	
	public boolean canRead() {
		return getFile().canRead();
	}

	public Reader getReader(){
		try {
			return new java.io.FileReader(getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getName() {
		return getFile().getAbsolutePath();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getName();
	}

	protected String locallySlashedPath(String fileWithPath) {
		String properlySlashedPath = fileWithPath;
		
		if(fileWithPath.indexOf('\\') != -1 && java.io.File.separator.equals("/"))
		{
			while(properlySlashedPath.indexOf('\\') != -1)
				properlySlashedPath = fileWithPath.replace('\\', '/');
		} else if(fileWithPath.indexOf('/') != -1 && java.io.File.separator.equals("\\")) {
			while(properlySlashedPath.indexOf('/') != -1)
				properlySlashedPath = fileWithPath.replace('/', '\\');
		} 
		
		return properlySlashedPath;
	}

	@Override
	public FileInputStream getInputStream() {
		try {
			return new FileInputStream(getFile());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not create Input Stream", e);
		}
	}
}
