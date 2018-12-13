package yogi.remote.server.app;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import yogi.base.GetPublicHostname;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Processor;
import yogi.remote.server.CommandServerImpl;

public class CommandServerProcessor implements Processor {
	public static String 	ServerLookupName 	= "CommandServer";
	public static int 		RegistryPort 		= 9998;	
	
	public boolean RUN = true;
	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		try {
			CommandServerImpl stub = new CommandServerImpl();
			Registry registry = LocateRegistry.createRegistry(RegistryPort);
			registry.bind(ServerLookupName, stub);
			ErrorReporter.get().info(String.format("Command Server registered as %s with the RMI Server on port: %s of machine: %s", ServerLookupName, RegistryPort, getHostNameAndIPAddress()));
		} catch (Exception exception) {
			Object message = "Server exception: " + exception.getMessage();
			ErrorReporter.get().error(message, exception);
		}
	}

	private String getHostNameAndIPAddress() {
		String host = "";
		try {
			List<InetAddress> inetAddressesIgnoringLocalHost = GetPublicHostname.getInetAddressesIgnoringLocalHost();
			for(InetAddress ia: inetAddressesIgnoringLocalHost)
			{
				host += ia.getCanonicalHostName() + " " + ia.getHostAddress()+";";
			}
			if(host.isEmpty()){
				host = "LocalHost";
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return host;
	}

}
