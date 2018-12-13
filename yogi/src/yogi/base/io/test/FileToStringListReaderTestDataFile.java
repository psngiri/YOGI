package yogi.base.io.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.base.Util;
import yogi.base.io.FileToStringListReader;

/**
 * @author Vikram Vadavala
 *
 */
public class FileToStringListReaderTestDataFile extends TestCase {
	public static String dataLocation = Util.getTestDataDirectoryPath();

	/*
	 * Test method for yogi.base.io.resource.ZipFileResource.getReader()
	 */
	public void test(){
		try {
			FileToStringListReader reader = new FileToStringListReader(dataLocation+"/"+"filetostringlistReaderTest.dat", 0);
			List<String> markets = reader.read();
			assertEquals(2, markets.size());
		} catch (Exception e) {
			fail("FileToStringListReader failed due to "+e);
		}		
	}
	
	
}
