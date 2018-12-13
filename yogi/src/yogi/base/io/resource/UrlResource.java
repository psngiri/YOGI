package yogi.base.io.resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class UrlResource implements SystemResource {
	private String url;

	public UrlResource(String url) {
		this.url = url;
	}

	public boolean canRead() {
		try {
			URL myURL = new URL(url);
			myURL.openConnection().connect();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	public Reader getReader(){
		return new InputStreamReader(getInputStream());
	}

	public String getName() {
		return url;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getName();
	}

	@Override
	public InputStream getInputStream() {
		try {
			URL myURL = new URL(url);
			return myURL.openStream();
		} catch (Exception e) {
			throw new RuntimeException("Could not create reader for url:"+ url);
		}
	}

}
