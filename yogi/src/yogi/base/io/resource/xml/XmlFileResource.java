package yogi.base.io.resource.xml;

import yogi.base.io.resource.FileResource;

public class XmlFileResource extends XmlSystemResource{
	public XmlFileResource(String fileName, String tagName) {
		super(new FileResource(fileName), tagName);
	}
}
