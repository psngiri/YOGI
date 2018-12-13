package yogi.base.io.resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import yogi.property.PropertyReplacer;

public class ClassPathResource implements SystemResource{
	private String resourceName;
	private Class<?> refClass = this.getClass();
	
	public ClassPathResource(String resourceName) {
		super();
		this.resourceName = resourceName;
	}

	public ClassPathResource(String resourceName, Class<?> refClass) {
		this(resourceName);
		this.refClass = refClass;
	}

	public boolean canRead() {
		return getInputStream() != null;
	}

	public Reader getReader(){
		return new InputStreamReader(getInputStream());
	}

	public String getName() {
		return resourceName;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getName();
	}

	public InputStream getInputStream() {
		resourceName = new PropertyReplacer().replaceVariables(resourceName);
		return refClass.getResourceAsStream(resourceName);
	}
}
