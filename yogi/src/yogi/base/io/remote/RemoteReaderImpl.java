package yogi.base.io.remote;

import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;

import yogi.base.app.ErrorReporter;
import yogi.base.io.resource.SystemResource;
import yogi.remote.UnicastRemoteObject;

public class RemoteReaderImpl extends UnicastRemoteObject implements RemoteReader {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254599939078329717L;
	private Reader reader;

	public RemoteReaderImpl(SystemResource systemResource) throws IOException {
		super();
		if (systemResource.canRead()) {
			ErrorReporter.get().info(
					"Reading from Resource - " + systemResource.getName());
			this.reader = systemResource.getReader();
		} else {
			ErrorReporter.get().warning(
					"Could not read from Resource - "
							+ systemResource.getName());
		}
	}

	public RemoteReaderImpl(Reader reader) throws RemoteException {
		super();
		this.reader = reader;
	}

	public void close() throws RemoteException {
		try {
			reader.close();
		} catch (IOException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public char[] readRemote(int len) throws RemoteException {
		try {
			char[] buf = new char[len];
			int count = reader.read(buf);
			if (count == -1)
				return null;
			char[] rtnValue = new char[count];
			System.arraycopy(buf, 0, rtnValue, 0, count);
			return rtnValue;
		} catch (IOException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

}
