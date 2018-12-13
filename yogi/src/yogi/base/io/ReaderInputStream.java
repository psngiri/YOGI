package yogi.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class ReaderInputStream extends InputStream {
	java.io.Reader reader;
	public ReaderInputStream(Reader reader) {
		super();
		this.reader = reader;
	}
	
	@Override
	public int read() throws IOException {
		return reader.read();
	}

	@Override
	public int available() throws IOException {
		throw new RuntimeException("Not Supported");
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		try {
			reader.mark(readlimit);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean markSupported() {
		return reader.markSupported();
	}

	@Override
	public synchronized void reset() throws IOException {
		reader.reset();
	}

	@Override
	public long skip(long n) throws IOException {
		return reader.skip(n);
	}

}
