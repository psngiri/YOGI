package yogi.base.io.resource.xml;

import org.w3c.dom.NodeList;

import yogi.base.io.resource.Resource;

public interface XmlResource extends Resource{
	NodeList getNodes();
}
