package yogi.base.io.test;

import junit.framework.TestCase;

import yogi.base.io.CharArrayReader;
import yogi.base.io.CharArrayReader.Line;

/**
 * @author Vikram Vadavala
 *
 */
public class CharArrayReaderTestS extends TestCase {

	/*
	 * Test method for yogi.base.io.resource.ZipFileResource.getReader()
	 */
	public void test(){
			CharArrayReader reader = new CharArrayReader("src/com/aa/cp/base/io/test/testFile.txt", 16);
			reader.setNumberOfLinesInBuffer(5);
			reader.open();
			while(reader.hasNext()){
				Line next = reader.next();
				System.out.println(next.getTrim(0, 7)+","+next.getTrim(7, 14));
			}
			reader.close();
	}
	
	
}
