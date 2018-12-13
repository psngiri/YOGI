/**
 * 
 */
package yogi.base.io.resource.db.msaccess;

import java.io.FileFilter;

import yogi.base.io.resource.DirectoryMultiResource;
import yogi.base.io.resource.db.MultiDbResource;


public class DirectoryMultiMSAccessDbResource extends DirectoryMultiResource implements MultiDbResource
{
	public DirectoryMultiMSAccessDbResource(String directoryName, FileFilter fileFilter) {
		super(directoryName, fileFilter);
	}

	public MSAccessDbResource next() {
		return new MSAccessDbResource(getFileIterator().next().getPath());
	}
}