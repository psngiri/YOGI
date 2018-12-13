package yogi.base.io.xml;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import yogi.base.io.resource.SystemResource;

public class XmlUtil {
	public static String getAttributeValue(Node node, String attributeName) {
		if(node == null) return null;
		Node attr = node.getAttributes().getNamedItem(attributeName);
		if(attr == null) return null;
		String nodeValue = attr.getNodeValue();
		return nodeValue;
	}
	
	public static Document getDocument(String fileName) {
		File file = new File(fileName);
		return getDocument(file);
	}

	private static Document getDocument(File file) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware( true );
        Document document;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return document;
	}

	public static Document getDocument(SystemResource resource) {
		if (!resource.canRead()) {
			throw new RuntimeException("Could not read from " + resource);
		}
		return getDocument(resource.getReader());
	}

	public static Document getDocument(Reader reader) {
		return getDocument(new InputSource(reader));
	}

	public static Document getDocument(InputStream inputStream) {
		return getDocument(new InputSource(inputStream));
	}
	
	public static Document getDocument(InputSource inputSource) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware( true );
        Document document;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(inputSource);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return document;
	}

}
