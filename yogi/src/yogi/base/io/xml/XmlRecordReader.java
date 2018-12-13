package yogi.base.io.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import yogi.base.io.RecordReader;
import yogi.base.io.resource.Resource;
import yogi.base.io.resource.xml.XmlResource;

public class XmlRecordReader implements RecordReader<Node> {
	private XmlResource resource;
	private NodeList nodes;
	private int currentIndex = 0;
	
	public XmlRecordReader(XmlResource resource) {
		super();
		this.resource = resource;
	}

	public boolean open() {
		nodes = resource.getNodes();
		return true;
	}

	public boolean close() {
		resource = null;
		nodes = null;
		return true;
	}

	public boolean hasNext() {
		return (currentIndex<nodes.getLength());
	}

	public Node next() {
		Node item = nodes.item(currentIndex);
		currentIndex = currentIndex + 1;
		return item;
	}

	public Resource getResource() {
		return resource;
	}
	

}
