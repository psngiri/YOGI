package yogi.base.io.resource.xml;

import java.io.InputStream;
import java.io.Reader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import yogi.base.io.resource.StringResource;
import yogi.base.io.resource.SystemResource;

public class SystemXmlResource implements SystemResource{
	private XmlResource resource;
	private StringResource stringResource;
	private String appendData;

	public SystemXmlResource(XmlResource resource) {
		this(resource, null);
	}

	public SystemXmlResource(XmlResource resource, String appendData) {
		super();
		this.resource = resource;
		this.appendData = appendData;
	}

	public String getName() {
		return resource.getName();
	}

	public boolean canRead() {
		return getStringResource().canRead();
	}

	public Reader getReader() {
		Reader reader = getStringResource().getReader();
		stringResource = null;
		return reader;
	}

	private StringResource getStringResource() {
		if(stringResource == null)
		{
			NodeList nodes = resource.getNodes();
			int length = nodes.getLength();
			if(length != 1) throw new RuntimeException("Expect only one Node, found:"+ length);
			Node node = nodes.item(0);
			Node item2 = node.getFirstChild();
			String data = item2.getNodeValue();
			if(appendData != null) data = data + appendData;
			stringResource = new StringResource(data, "");
		}
		return stringResource;
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
