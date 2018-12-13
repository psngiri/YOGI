package yogi.filecommand.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;
import yogi.filecommand.FileCommand;
import yogi.filecommand.FileCommandFactory;
import yogi.filecommand.FileCommandValidator;


/**
 * @author Vikram Vadavala
 *
 */
public class FileCommandReader extends DefaultStringRecordReader<FileCommand> {
	public static boolean RUN = true;
	
	public FileCommandReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<FileCommand, String>(new FileCommandValidator(), new FileCommandScanner(), FileCommandFactory.get(), new FileCommandRecordSelector()));
	}

	public FileCommandReader(Collection<String> filesLoaders) {
		super(filesLoaders);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
