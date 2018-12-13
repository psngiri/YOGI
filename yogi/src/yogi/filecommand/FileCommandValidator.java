package yogi.filecommand;

import yogi.base.app.ErrorReporter;
import yogi.base.validation.ValidatorAdapter;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandValidator extends ValidatorAdapter<FileCommand> {
	@Override
	public boolean validate(FileCommand fileCommand) {
		if(fileCommand.getFileName()==null || fileCommand.getCommand()==null){
			ErrorReporter.get().warning("Could Not create object as FileName or Command is null for ",fileCommand);
			return false;
		}
		return true;
	}
}
