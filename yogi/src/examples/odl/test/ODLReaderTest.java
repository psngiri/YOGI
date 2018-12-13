package examples.odl.test;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;
import examples.odl.ODL;
import examples.odl.ODLAttribute;
import examples.odl.ODLInterface;
import examples.odl.ODLReader;

public class ODLReaderTest extends TestCase {

	public void test(){
		Pattern p = Pattern.compile("^\\s*attribute\\s*([a-zA-Z]*)\\d*\\s*(\\w+)\\s*;\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*>");
		
		String input = "  attribute char1    serviceType;     <POS=\"0\", SIZE=\"1\">";
		System.out.println(input);
		Matcher m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
		}
		p = Pattern.compile("^\\s*attribute\\s*([a-zA-Z]*)\\d*\\s*(\\w+)\\s*;\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*,\\s*DECIMAL=\"(\\d+)\"\\s*>");
		input = "  attribute char    marketSize;     <POS=\"82\", SIZE=\"14\", DECIMAL=\"6\">";
		m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
			System.out.println(m.group(5));
		}
		p = Pattern.compile("^\\s*interface\\s*(\\S*)");
		input = "  interface esDuplicateODServiceOut1";
		m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}
	
	public void testInterfaceDifferentiator(){
		Pattern p = Pattern.compile("^\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*,\\s*VALUE=\\s*\"(.+)\"\\s*>");
		String input = "<POS=\"0\",SIZE=\"1\",VALUE=\"*\">";
		Matcher m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
		
	}
	
	public void testKey(){
		Pattern p = Pattern.compile("^\\s*key\\s*(\\w+);");
		String input = "  key ODkey;";
		Matcher m = p.matcher(input);
		while (m.find()) {
			System.out.println(m.group(1));
		}
		
	}
	
	public void testReadNonStopOp(){
		String fileName = "*/examples/odl/test/NonStopOpOutDirect.odl";
		ODLReader reader = new ODLReader(fileName);
		ODL odl = reader.read();
		for(Entry<String, ODLInterface> myEntry: odl.getInterfaces().entrySet()){
			System.out.println(myEntry.getKey());
			ODLInterface odlInterface = myEntry.getValue();
			System.out.println(odlInterface.getDifferentiator());
			System.out.println(odlInterface.getKey());
			for(Entry<String, ODLAttribute> entry: odlInterface.getAttributes().entrySet()){
				System.out.println(entry);
			}
		}
	}

	public void testReadODService(){
		String fileName = "*/examples/odl/test/ODServiceOutDirect.odl";
		ODLReader reader = new ODLReader(fileName);
		ODL odl = reader.read();
		for(Entry<String, ODLInterface> myEntry: odl.getInterfaces().entrySet()){
			System.out.println(myEntry.getKey());
			ODLInterface odlInterface = myEntry.getValue();
			for(Entry<String, ODLAttribute> entry: odlInterface.getAttributes().entrySet()){
				System.out.println(entry);
			}
		}
	}
	public void testReadODServiceBin(){
		String fileName = "*/examples/odl/test/ODServiceOutBinDirect.odl";
		ODLReader reader = new ODLReader(fileName);
		ODL odl = reader.read();
		for(Entry<String, ODLInterface> myEntry: odl.getInterfaces().entrySet()){
			System.out.println(myEntry.getKey());
			ODLInterface odlInterface = myEntry.getValue();
			for(Entry<String, ODLAttribute> entry: odlInterface.getAttributes().entrySet()){
				System.out.println(entry);
			}
		}
	}
}
