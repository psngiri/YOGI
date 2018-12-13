package yogi.base.io.xml;

import org.w3c.dom.Node;

import yogi.base.io.Formatter;

public abstract class XmlFormatter implements Formatter<Node> {

	protected String getAttributeValue(Node node, String attributeName) {
		return XmlUtil.getAttributeValue(node, attributeName);
	}

}
