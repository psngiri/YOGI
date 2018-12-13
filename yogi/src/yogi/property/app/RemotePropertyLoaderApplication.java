package yogi.property.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.property.command.PropertyReaderCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;


public class RemotePropertyLoaderApplication {
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage: RemotePropertyLoaderApplication remoteServerAddress fileName");
			System.out.println("Example: RemotePropertyLoaderApplication localhost:5088 D:\\workspace\\PRE\\data\testDom\\properties.dat");
			System.out.println("Usage: RemotePropertyLoaderApplication remoteServerAddress property1 property2 ...");
			System.out.println("Example: RemotePropertyLoaderApplication localhost:5088 yogi.base.io.db.dump.DumpProperties:WriteToDbDump=true yogi.base.io.db.dump.DumpProperties:LoadToDb=false");
			return;
		}
		BaseCommandExecutor.Initialized = true;
		CommandExecutor.commandServerAddressesColonPortNumbers = args[0];
		CommandExecutor.UsePull=true;
		try {
			if(args.length == 2 && new File(args[1]).isFile())
			{
				CommandExecutor.get().execute(new PropertyReaderCommand(args[1]));
			}else
			{
				List<String> argsAsList = Arrays.asList(args);
				List<String> properties = new ArrayList<String>(argsAsList.subList(1, args.length));
				CommandExecutor.get().execute(new PropertyReaderCommand(properties));
			}
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}

}
