package yogi.base.io.resource;

import java.io.InputStream;
import java.io.Reader;

public interface SystemResource extends Resource {
	boolean canRead();
	Reader getReader();
	InputStream getInputStream();
}
