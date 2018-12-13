/**
 * 
 */
package yogi.base.io.resource;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Iterator;

import yogi.property.PropertyReplacer;


public abstract class DirectoryMultiResource implements MultiResource
{
	private String directoryName;
	private Iterator<File> fileIterator;
	private FileFilter fileFilter;

	public DirectoryMultiResource(String directoryName, FileFilter fileFilter) {
		super();
		this.directoryName = directoryName;
		this.fileFilter = fileFilter;
	}

	void constructFileIterator()
	{
		File file = new File(new PropertyReplacer().replaceVariables(getDirectoryName()));
		if(!file.canRead()) throw new RuntimeException(String.format("Provided Directory Name:%1$s is not accessable for Reading", directoryName));
		if(!file.isDirectory()) throw new RuntimeException(String.format("Provided Directory Name:%1$s is not a valid Directory", directoryName));
		File[] files = file.listFiles(getFileFilter());
		if(files == null) throw new RuntimeException(String.format("Provided Directory Name:%1$s is not valid", directoryName));
		fileIterator = Arrays.asList(files).iterator();
	}
	
	public String getDirectoryName() {
		return directoryName;
	}

	public FileFilter getFileFilter() {
		return fileFilter;
	}

	protected void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	protected void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	protected Iterator<File> getFileIterator() {
		if(fileIterator == null)
		{
			constructFileIterator();
		}
		return fileIterator;
	}

	public boolean hasNext() {
		return getFileIterator().hasNext();
	}

	public abstract Resource next();

	public String getName() {
		return directoryName;
	}
	
}