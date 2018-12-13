package yogi.base.io.xml.test;

import junit.framework.TestCase;

import yogi.base.io.resource.xml.SystemXmlResource;
import yogi.base.io.resource.xml.XmlFileResource;

public class SystemXmlResourceTestDataFile extends TestCase {

	public void test()
	{
		String fileName = "data/xmlTestData/SEAmctSabreCommandWS_payLoad.xml";
		XmlFileResource xmlFileResource = new XmlFileResource(fileName, "Response");
		SystemXmlResource resource = new SystemXmlResource(xmlFileResource);
		assertTrue(resource.canRead());
	}
}
