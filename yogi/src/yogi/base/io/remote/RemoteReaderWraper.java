package yogi.base.io.remote;

import java.io.IOException;
import java.io.Reader;

public class RemoteReaderWraper extends Reader {
	private RemoteReader remoteReader;
	
	public RemoteReaderWraper(RemoteReader remoteReader) {
		super();
		this.remoteReader = remoteReader;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		char[] buf = remoteReader.readRemote(len);
		if(buf == null) return -1;
		int count = buf.length;
		System.arraycopy(buf, 0, cbuf, off, count);
		return count;
	}

	@Override
	public void close() throws IOException {
		remoteReader.close();
	}

}
