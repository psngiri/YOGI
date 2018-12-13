/**
 * 
 */
package yogi.base.io.resource;

import java.io.FileFilter;


public class DirectoryMultiFileResource extends DirectoryMultiResource implements MultiFileResource
{
	public DirectoryMultiFileResource(String directoryName, FileFilter fileFilter) {
		super(directoryName, fileFilter);
	}

	public FileResource next() {
		return new FileResource(getFileIterator().next());
	}
}