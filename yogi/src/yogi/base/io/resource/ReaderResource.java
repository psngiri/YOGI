package yogi.base.io.resource;

import java.io.InputStream;
import java.io.Reader;

public class ReaderResource implements SystemResource {
	private Reader reader;
	private String name;
	
	public ReaderResource(Reader reader, String name) {
		super();
		this.reader = reader;
		this.name = name;
	}

	public boolean canRead() {
		return true;
	}

	public Reader getReader() {
		return reader;
	}

	public String getName() {
		return name;
	}

	@Override
	public InputStream getInputStream() {
		throw new RuntimeException("Not implemented try to use another way or implement this method properly");
	}

}
