package yogi.server.gui.purge.command;

import yogi.base.app.Executor;
import yogi.base.app.Module;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.server.CommandServerImpl;

/**
 * @author Vikram Vadavala
 *
 */
public abstract class PurgeCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = 1L;
	public static int daysToKeep=7;
	
	public PurgeCommand() {
		super(null);
	}

	@Override
	public Boolean execute() {
		CommandServerImpl.setServerAlive(false);//server offline
		try {
			runModule(getModule());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			CommandServerImpl.setServerAlive(true);//server online
		}
		return true;
	}
	
	private void runModule(Module module){
		Executor.get().setup(module);
		module.run();
	}
	
	protected long getPurgeTimeStamp(){
		long currentTimeMillis = System.currentTimeMillis();
		return currentTimeMillis-(daysToKeep*86400000);
	}
		
	protected abstract Module getModule();
	
}
