package yogi.optimize.remote.server;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yogi.optimize.remote.client.CplexJavaConnectServer;
import yogi.optimize.remote.client.ServerConnectionUtil;
import yogi.optimize.remote.client.Util;

public class CplexJavaConnectServerImpl extends UnicastRemoteObject implements CplexJavaConnectServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746451174455823795L;
	

	
	protected CplexJavaConnectServerImpl() throws RemoteException {
		super();
	}

	public StringBuffer getIPSolution(String homeDirectory, String cplexCommand, StringBuffer matrixFileContent) throws RemoteException {
		homeDirectory = Util.checkAndCreateHomeDirectory(homeDirectory);
		if ( cplexCommand == null ) {
			cplexCommand = CplexJavaConnectServer.DEFAULT_CPLEX_COMMAND;
		}
		String tempDirectoryName = null;
		File matrixFile;
		try {
			tempDirectoryName = Util.createTempDirectory(homeDirectory);
			matrixFile = new File(tempDirectoryName + "/matrix.mps");
			Util.createFileFromStringBuffer(matrixFile, matrixFileContent);
			System.out.println("Matrix file received " + matrixFile.getAbsolutePath() );
			System.out.println("Cplex command " + String.format("%1$s %2$s", cplexCommand, tempDirectoryName));
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command( getTokens(String.format("%1$s %2$s", cplexCommand, tempDirectoryName)));
			Process process = processBuilder.start();
			int exitStatus = process.waitFor();
			if(exitStatus < 0) { 
				String msg = matrixFile.getAbsolutePath() + " exited with error status: " + exitStatus;
				System.out.println(msg);
				new RuntimeException( msg );
			}
			System.out.println("IPSolution created at " + tempDirectoryName );
			return Util.getFileIntoStringBuffer(tempDirectoryName + "/" + IPSOLUTION_FILE);
		} catch (IOException e) {
			String msg = "Server internal IOException, message is .. " + e.getMessage();
			System.out.println(msg);
			throw new RemoteException(msg);
		} catch (InterruptedException e) {
			String msg = "Server internal InterruptedException, message is .. " + e.getMessage();
			System.out.println(msg);
			throw new RemoteException("Server internal InterruptedException, message is .. " + e.getMessage());
		}
	}
	
	private List<String> getTokens(String command) {
		Scanner scanner = new Scanner(command);
		List<String> tokens = new ArrayList<String>();
		while(scanner.hasNext())
		{
			tokens.add(scanner.next());
		}
		return tokens;
	}
	
	public static void main(String args[]) {
		try {
			CplexJavaConnectServerImpl stub = new CplexJavaConnectServerImpl();
			Registry registry = ServerConnectionUtil.createRegistry();
			registry.bind(CplexJavaConnectServer.SERVER_LOOKUP_NAME, stub);
			System.err.println("Server started");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public boolean isServerAlive() throws RemoteException {
		return true;
	}
	
}
