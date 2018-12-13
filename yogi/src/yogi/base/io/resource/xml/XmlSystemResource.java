package yogi.base.io.resource.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import yogi.base.io.resource.SystemResource;
import yogi.base.io.xml.XmlUtil;

public class XmlSystemResource implements XmlResource{
	private SystemResource resource;
	private String tagName;

	public XmlSystemResource(SystemResource resource, String tagName) {
		super();
		this.resource = resource;
		this.tagName = tagName;
	}

	public NodeList getNodes() {
		Document document = XmlUtil.getDocument(resource);
		return document.getElementsByTagName(tagName);
	}

	public String getName() {
		return resource.getName() + ":" + tagName;
	}

}
