package yogi.filecommand.app;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.BaseModule;
import yogi.filecommand.io.FileCommandReader;

public class FileCommandModule extends BaseModule {
	public static boolean RUN = true;
	private String commandsFileName;
	
	public FileCommandModule(String fileName) {
		super();
		this.commandsFileName=fileName;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addReader(new FileCommandReader(commandsFileName));
        addProcessor(new FileCommandExecutorLoopModule());
	}

}
