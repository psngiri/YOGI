package yogi.remote.simple;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

public class CommandServerApp  {
	public static String 	ServerLookupName 	= "CommandServer";
	public static int 		RegistryPort 		= 9998;	
	private static Logger logger = Logger.getLogger(CommandServerApp.class.getName());

	public void run() {
		try {
			CommandServerImpl stub = new CommandServerImpl();
			Registry registry = LocateRegistry.createRegistry(RegistryPort);
			registry.bind(ServerLookupName, stub);
			logger.info(String.format("Command Server registered as %s with the RMI Server on port: %s of machine: %s", ServerLookupName, RegistryPort, getHostNameAndIPAddress()));
		} catch (Exception exception) {
			Object message = "Server exception: " + exception.getMessage();
			logger.severe(message +  exception.toString());
			exception.printStackTrace();
		}
	}

	private String getHostNameAndIPAddress() {
		String host = "LocalHost";
		try {
			List<InetAddress> inetAddressesIgnoringLocalHost = getInetAddressesIgnoringLocalHost();
			if(!inetAddressesIgnoringLocalHost.isEmpty())
			{
				InetAddress ia = inetAddressesIgnoringLocalHost.get(0);
				host = ia.getCanonicalHostName() + " " + ia.getHostAddress();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return host;
	}

	public static List<InetAddress> getInetAddressesIgnoringLocalHost() throws Throwable
	{
		List<InetAddress> rtnValue = new ArrayList<InetAddress>();
		NetworkInterface iface = null;
		for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
				.hasMoreElements();) {
			iface = (NetworkInterface) ifaces.nextElement();
			InetAddress ia = null;
			for (Enumeration<InetAddress> ips = iface.getInetAddresses(); ips
					.hasMoreElements();) {
				ia = (InetAddress) ips.nextElement();
				if(ia.getCanonicalHostName().equalsIgnoreCase("localhost")) continue;
				rtnValue.add(ia);
			}
		}
		return rtnValue;
	}
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: CommandServerApp portNumber");
			return;
		}
		RegistryPort = Integer.parseInt(args[0]);
		CommandServerApp app = new CommandServerApp();
		app.run();
	}
}
