package yogi.base.io.resource.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlDocumentResource implements XmlResource{
	private Document document;
	private String tagName;
	
	public XmlDocumentResource(Document document, String tagName) {
		super();
		this.document = document;
		this.tagName = tagName;
	}

	public NodeList getNodes() {
		return document.getElementsByTagName(tagName);
	}

	public String getName() {
		return tagName;
	}

}
