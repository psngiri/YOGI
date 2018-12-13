package yogi.filecommand.app;

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
public class FileCommandExecutorListenerProcessor extends BaseMultiThreadFileExecutorListenerProcessor<FileCommandProcessor>{
	
	public static boolean RUN = true;

	public FileCommandExecutorListenerProcessor() {
		super(FileCommandProcessor.class);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
		
	

