package yogi.base.io.xml.test;

import junit.framework.TestCase;

import org.w3c.dom.Node;

import yogi.base.io.resource.xml.XmlFileResource;
import yogi.base.io.xml.XmlRecordReader;

public class XmlRecordReaderTestDataFile extends TestCase {

	public void test()
	{
		String fileName = "data/xmlTestData/DFWmct.xml";
		XmlFileResource xmlFileResource = new XmlFileResource(fileName, "HeaderInfo");
		XmlRecordReader xmlRecordReader = new XmlRecordReader(xmlFileResource);
		xmlRecordReader.open();
		while(xmlRecordReader.hasNext())
		{
			Node node = xmlRecordReader.next();
			getAttributValueAndWrite(node, "Ind", " ");
			Node item = node.getFirstChild();
			if(item.getNodeName().equals("IATAProcessing"))
			{
				getAttributValueAndWrite(item, "Ind", " ");
				item = item.getNextSibling();
			}
			if(item.getNodeName().equals("MinConnectTime"))
			{
				Node item2 = item.getFirstChild();
				String mctValue = item2.getNodeValue();
				System.out.println(" " + mctValue);
			}
		}
	}
	public void test1()
	{
		String fileName = "data/xmlTestData/DFWmct.xml";
		XmlFileResource xmlFileResource = new XmlFileResource(fileName, "AirlineInfo");
		XmlRecordReader xmlRecordReader = new XmlRecordReader(xmlFileResource);
		xmlRecordReader.open();
		while(xmlRecordReader.hasNext())
		{
			Node node = xmlRecordReader.next();
			processAirlineInfo(node);
//			Node child = node.getFirstChild();
//			while(child != null)
//			{
//				processAirlineInfo(child);
//				child = child.getNextSibling();
//			}
		}
	}
	private void processAirlineInfo(Node node) {
		if(!node.getNodeName().equals("AirlineInfo")) return;
		Node item = node.getFirstChild();
		if(item != null && item.getNodeName().equals("Inbound"))
		{
			getAttributValueAndWrite(item, "Code", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Direction"))
		{
			getAttributValueAndWrite(item, "Code", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Outbound"))
		{
			getAttributValueAndWrite(item, "Code", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("IATAProcessing"))
		{
			getAttributValueAndWrite(item, "Ind", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("MinConnectTime"))
		{
			Node item2 = item.getFirstChild();
			String mctValue = item2.getNodeValue();
			System.out.print(" " + mctValue);
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Equipment"))
		{
			getAttributValueAndWrite(item, "AirEquipType", " ");
			item = item.getNextSibling();
		}
		while(item != null && item.getNodeName().equals("FlightNumbers"))
		{
			Node child = item.getFirstChild();
			getAttributValueAndWrite(child, "FlightNumber", " ");
			child = child.getNextSibling();
			getAttributValueAndWrite(child, "FlightNumber", " - ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Generic"))
		{
			getAttributValueAndWrite(item, "Ind", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("RegionDescription"))
		{
			getAttributValueAndWrite(item, "Text", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Type"))
		{
			String nodeValue = getAttributValue(item, "Ind");
			System.out.print(" " + nodeValue );
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("Text"))
		{
			String nodeValue = item.getFirstChild().getNodeValue();
			System.out.print(" " + nodeValue );
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("DepartureAirport"))
		{
			getAttributValueAndWrite(item, "LocationCode", " ");
			getAttributValueAndWrite(item, "CodeContext", " ");
			getAttributValueAndWrite(item, "ArrivalTerminal", " ");
			item = item.getNextSibling();
		}
		if(item != null && item.getNodeName().equals("ArrivalAirport"))
		{
			getAttributValueAndWrite(item, "LocationCode", " ");
			getAttributValueAndWrite(item, "CodeContext", " ");
			getAttributValueAndWrite(item, "ArrivalTerminal", " ");
			item = item.getNextSibling();
		}
		System.out.println();
	}
	private void getAttributValueAndWrite(Node node, String name, String separator)
	{
		String nodeValue = getAttributValue(node, name);
		write(nodeValue, separator);
	}
	private void write(String nodeValue, String separator) {
		if(nodeValue != null) System.out.print(separator + nodeValue );
	}
	private String getAttributValue(Node node, String name) {
		if(node == null) return null;
		Node attr = node.getAttributes().getNamedItem(name);
		if(attr == null) return null;
		String nodeValue = attr.getNodeValue();
		return nodeValue;
	}
}
