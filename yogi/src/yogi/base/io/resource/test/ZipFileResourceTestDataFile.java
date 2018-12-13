package yogi.base.io.resource.test;

import java.io.Reader;
import junit.framework.TestCase;
import yogi.base.Util;
import yogi.base.io.resource.ZipFileResource;

/**
 * @author Vikram Vadavala
 *
 */
public class ZipFileResourceTestDataFile extends TestCase {
	public static String dataLocation = Util.getTestDataDirectoryPath() + "/ZipFileResourceTest/";

	/*
	 * Test method for yogi.base.io.resource.ZipFileResource.getReader()
	 */
	public void testgetReader(){
		try {
			ZipFileResource zipFileResource = new ZipFileResource(dataLocation+"Test.zip");
			Reader reader = zipFileResource.getReader();
			if(reader==null)fail("ZipFileReader is null");
		} catch (Exception e) {
			fail("ZipFileReaderTest failed due to "+e);
		}		
	}
	
	
}
