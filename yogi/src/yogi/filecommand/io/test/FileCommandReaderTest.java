package yogi.filecommand.io.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.app.testing.TestModule;
import yogi.base.util.immutable.ImmutableList;
import yogi.filecommand.FileCommand;
import yogi.filecommand.FileCommandManager;
import yogi.filecommand.io.FileCommandReader;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandReaderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	
	public void test()
	{
		ArrayList<String> filesLoaders = new ArrayList<String>();
		filesLoaders.add("file1, ,command1,arg1 arg2 arg3");
		filesLoaders.add("file2, ,command2,arg1 arg2 arg3");
		filesLoaders.add("file3, ,command3,arg1 arg2 arg3");
		filesLoaders.add(",command4, ,arg1 arg2 arg3");//if file name is empty,that object wont get created
		FileCommandReader filesLoaderReader = new FileCommandReader(filesLoaders);

		TestModule testModule = new TestModule();
		testModule.addReader(filesLoaderReader);
		testModule.run();
		
		ImmutableList<FileCommand> fileLoaders = FileCommandManager.get().findAll();
		assertEquals(3, fileLoaders.size());
		
	    
    }
}
