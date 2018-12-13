package examples.odl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yogi.base.io.FileRecordReader;
import yogi.base.io.resource.SystemResource;

public class ODLReader extends FileRecordReader  {
	private static Pattern attributePattern = Pattern.compile("^\\s*attribute\\s*([a-zA-Z]*)\\d*\\s*(\\w+)\\s*;\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*>");
	private static Pattern attributeDecimalPattern = Pattern.compile("^\\s*attribute\\s*([a-zA-Z]*)\\d*\\s*(\\w+)\\s*;\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*,\\s*DECIMAL=\"(\\d+)\"\\s*>");
	private static Pattern interfacePattern = Pattern.compile("^\\s*interface\\s*(\\S*)");
	private static Pattern interfaceDifferentiatorPattern = Pattern.compile("^\\s*<POS=\"(\\d+)\\s*\"\\s*,\\s*SIZE=\"(\\d+)\\s*\"\\s*,\\s*VALUE=\\s*\"(.+)\"\\s*>");
	private static Pattern interfaceKeyPattern = Pattern.compile("^\\s*key\\s*(\\w+);");
	
	public ODLReader(String fileName, int headerLineCount) {
		super(fileName, headerLineCount);
	}

	public ODLReader(String fileName) {
		super(fileName);
	}

	public ODLReader(SystemResource resource, int headerLineCount) {
		super(resource, headerLineCount);
	}

	public ODLReader(SystemResource resource) {
		super(resource);
	}

	public ODL read() {
		ODL rtnValue = new ODL();
		ODLInterface odlInterface = null;
		boolean open = this.open();
		if(open){
			while(this.hasNext()){
				String record = this.next();
				String parseInterface = parseInterface(record);
				if(parseInterface != null){
					odlInterface = new ODLInterface(parseInterface);
					rtnValue.getInterfaces().put(odlInterface.getInterfaceName(), odlInterface);
				}else{
					ODLAttribute attribute = parseAttributeDecimal(record);
					if(attribute != null){
						odlInterface.getAttributes().put(attribute.getName(), attribute);
					}else{
						attribute = parseAttribute(record);
						if(attribute != null){
							odlInterface.getAttributes().put(attribute.getName(), attribute);
						}else{
							ODLDifferentiator differentiator = parseDifferentiator(record);
							if(differentiator != null){
								odlInterface.setDifferentiator(differentiator);
							}else{
								ODLKey key = parseKey(record);
								if(key != null){
									odlInterface.setKey(key);
								}
							}
						}
					}
				}
			}
		}
		close();
		return rtnValue;
	}

	private String parseInterface(String record){
		Matcher m = interfacePattern.matcher(record);
		while (m.find()) {
			return m.group(1);
		}
		return null;
	}
	private ODLDifferentiator parseDifferentiator(String record){
		Matcher m = interfaceDifferentiatorPattern.matcher(record);
		while (m.find()) {
			return new ODLDifferentiator(m.group(1), m.group(2), m.group(3));
		}
		return null;
	}
	private ODLKey parseKey(String record){
		Matcher m = interfaceKeyPattern.matcher(record);
		while (m.find()) {
			return new ODLKey(m.group(1));
		}
		return null;
	}
	
	private ODLAttribute parseAttributeDecimal(String record){
		Matcher m = attributeDecimalPattern.matcher(record);
		while (m.find()) {
			return new ODLAttribute(m.group(1), m.group(2),m.group(3), m.group(4), m.group(5));
		}
		return null;
	}
	
	private ODLAttribute parseAttribute(String record) {
		Matcher m = attributePattern.matcher(record);
		while (m.find()) {
			return new ODLAttribute(m.group(1), m.group(2),m.group(3), m.group(4));
		}
		return null;
	}

}
