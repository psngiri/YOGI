package yogi.base.io.resource.test;

import java.io.File;
import java.io.FileFilter;
import yogi.base.Util;
import yogi.base.io.resource.DirectoryMultiFileResource;

import junit.framework.TestCase;

public class DirectoryMultiFileResourceTestDataFile extends TestCase {
	public String dataLocation = Util.getTestDataDirectoryPath() + "/exampleTestData";

	/*
	 * Test method for 'yogi.base.io.resource.DirectoryMultiFileResource.next()'
	 */
	public void testNext() {
		MyFileFilter fileFilter = new MyFileFilter();
		DirectoryMultiFileResource directoryMultiFileResource = new DirectoryMultiFileResource(dataLocation, fileFilter);
		if(directoryMultiFileResource.hasNext())
		{
			assertEquals("airports.dat", directoryMultiFileResource.next().getFile().getName());
		}
		if(directoryMultiFileResource.hasNext())
		{
			assertEquals("flights.dat", directoryMultiFileResource.next().getFile().getName());
		}
		if(directoryMultiFileResource.hasNext())
		{
			fail("Should not have any more matches");
		}
	}

	static class MyFileFilter implements FileFilter{

		public boolean accept(File pathname) {
			if(pathname.getName() == "airports.dat" || pathname.getName() == "flights.dat" ) return true;
			return false;
		}
	}
}
