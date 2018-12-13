package yogi.optimize.remote.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CplexJavaConnectServer extends Remote {
	
	public static String 	SERVER_LOOKUP_NAME 	= "CplexJavaConnectServer";
	public static String 	REGISTRY_IP_ADDRESS = "cpappd03.corp.amrcorp.com";
	public static String 	REGISTRY_IP_ADDRESS_ALTERNATIVE = "cpappd02.corp.amrcorp.com";
	public static int 		REGISTRY_PORT 		= 9998;
	public static String 	ROOT_DIRECTORY 		= "/cphome";
	public static String 	CPLEX_COMMAND_PARAMETER_NAME = "CplexCommand";
	public static String 	DEFAULT_CPLEX_COMMAND = "/nfs/dev02_test/cwefam/prototype/cplexsolver.sh"; // "/cphome/432122/cwefam/prototype/cplexsolver.sh";
	public static String 	IPSOLUTION_FILE 	= "IPsolution";
	
	
	public StringBuffer getIPSolution(String homeDirectory, String cplexCommand, StringBuffer matrix) throws RemoteException;
	
	public boolean isServerAlive() throws RemoteException;
		
}
