package yogi.remote.simple;

import java.rmi.RemoteException;

public class UnicastRemoteObject extends java.rmi.server.UnicastRemoteObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7849386973329478719L;
	public static int Port = 0;

	protected UnicastRemoteObject() throws RemoteException {
		super(Port);
	}

}
