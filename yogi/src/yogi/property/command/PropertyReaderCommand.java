package yogi.property.command;

import java.util.Collection;

import yogi.base.app.Executor;
import yogi.property.io.PropertyReader;
import yogi.remote.client.app.CommandAdapter;

public class PropertyReaderCommand extends CommandAdapter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6004553772276003403L;
	private String file;
	private Collection<String> properties;
	
	public PropertyReaderCommand(String file) {
		super(null);
		this.file = file;
	}
	
	public PropertyReaderCommand(Collection<String> properties) {
		super(null);
		this.properties = properties;
	}

	@Override
	public Boolean execute() {
		if(file != null)
		{
			Executor.get().execute(new PropertyReader(file));
		}else
		{
			Executor.get().execute(new PropertyReader(properties));
		}
		return true;
	}

}
