package yogi.base.io.resource;

import java.io.InputStream;
import java.io.Reader;

public class StringResource implements SystemResource {
	private String data;
	private String name;
	
	public StringResource(String data, String name) {
		this.data = data;
		this.name = name;
	}

	public boolean canRead() {
		return data != null;
	}

	public Reader getReader(){
		return new java.io.StringReader(data);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getName();
	}

	@Override
	public InputStream getInputStream() {
		throw new RuntimeException("Not implemented try to use another way or implement this method properly");
	}
}
