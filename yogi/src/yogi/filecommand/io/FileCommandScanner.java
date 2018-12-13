package yogi.filecommand.io;

import yogi.filecommand.FileCommand;
import yogi.filecommand.FileCommandCreator;

/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandScanner implements yogi.base.io.Scanner<FileCommand, String> {
	private FileCommandCreator creator = new FileCommandCreator();

	public FileCommandCreator scan(String record) {
		String subStrings[] = record.split(",");
		creator.setFileName(subStrings[0].trim().isEmpty() ? null : subStrings[0]);
		String isRegex = subStrings[1].trim();
		creator.setIsRegularExpression(isRegex.isEmpty() ? false : isRegex.equalsIgnoreCase("T"));
		creator.setCommand(subStrings[2].trim().isEmpty() ? null : subStrings[2]);
		creator.setArguments(subStrings[3].trim().isEmpty() ? null : subStrings[3]);
		return creator;
	}
	
}
