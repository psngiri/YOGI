package yogi.optimize.remote.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerConnectionUtil {

	public static Registry createRegistry() throws RemoteException {
		return LocateRegistry.createRegistry(CplexJavaConnectServer.REGISTRY_PORT);
	}
	
	public static CplexJavaConnectServer getCplexJavaConnectServer() throws  CplexJavaConnectException {
		try {
			return getCplexJavaConnectServer(CplexJavaConnectServer.REGISTRY_IP_ADDRESS, CplexJavaConnectServer.REGISTRY_PORT);
		} catch (Exception e) {
			try {
				return getCplexJavaConnectServer(CplexJavaConnectServer.REGISTRY_IP_ADDRESS_ALTERNATIVE, CplexJavaConnectServer.REGISTRY_PORT);
			} catch (Exception e1) {
				throw new CplexJavaConnectException( e.getMessage() + " and alternate address " + e1.getMessage() , e1);
			}
		}
	}
	
	public static CplexJavaConnectServer getCplexJavaConnectServer(String ipAddress, int port) throws Exception {
		Registry registry = LocateRegistry.getRegistry(ipAddress, port);
		CplexJavaConnectServer server =  (CplexJavaConnectServer)registry.lookup(CplexJavaConnectServer.SERVER_LOOKUP_NAME);
		if ( server.isServerAlive()) {
			return server;
		}
		throw new CplexJavaConnectException( ipAddress + ":" + port + " Server not alive");
	}
}
