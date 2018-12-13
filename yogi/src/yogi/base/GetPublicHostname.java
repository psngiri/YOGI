package yogi.base;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GetPublicHostname {
	public static void main(String[] args) throws Throwable {
		NetworkInterface iface = null;
		for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
				.hasMoreElements();) {
			iface = (NetworkInterface) ifaces.nextElement();
			System.out.println("Interface:" + iface.getDisplayName());
			InetAddress ia = null;
			for (Enumeration<InetAddress> ips = iface.getInetAddresses(); ips
					.hasMoreElements();) {
				ia = (InetAddress) ips.nextElement();
				System.out.println(ia.getCanonicalHostName() + " "
						+ ia.getHostAddress());
			}
		}
		for(InetAddress ia: getInetAddressesIgnoringLocalHost())
		{
			System.out.println(ia.getCanonicalHostName() + " "
					+ ia.getHostAddress());
		}
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
}
