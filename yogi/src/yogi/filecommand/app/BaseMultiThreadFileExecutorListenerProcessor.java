package yogi.filecommand.app;

import java.io.File;
import java.util.List;

import yogi.base.app.multithread.MultiCaller;
/**
 * @author Vikram Vadavala
 * 
 * Description:
 * This Processor executes commands that are given in commandsFile.txt
 * for a file and backs it up in the BackupDir after finising executing 
 * commands on the file if BackupDir is not null
 * it also moves the done file for that file to BackupDir if done file exists and BackupDir is not null and DeleteDoneFile is false
 * if BackupDir is null or DeleteDoneFile is true then it will delete done file
 *
 * Note:
 * The done file must have the same name as the original file followed by a suffix 
 * for eg: filename.done
 */
public abstract class BaseMultiThreadFileExecutorListenerProcessor<T extends BaseFileProcessor> extends BaseFileExecutorListenerProcessor{
	
	private Class <T> fileProcessorClass;
	private T fileProcessor;
	public BaseMultiThreadFileExecutorListenerProcessor(Class <T> fileProcessorClass) {
		super();
		this.fileProcessorClass=fileProcessorClass;
		try {
			fileProcessor = fileProcessorClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public T getFileProcessor() {
		return fileProcessor;
	}

	protected void processFiles(List<File> files) {
		new MultiCaller<File>().call(files, fileProcessorClass);//will use a MultiThread Executor to process each file in a separate Thread
	}

}
		
	

