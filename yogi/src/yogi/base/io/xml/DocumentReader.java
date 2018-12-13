package yogi.base.io.xml;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import yogi.base.io.FileToStringReader;
import yogi.base.io.resource.SystemResource;
import yogi.property.KeyAndPropertyReplacer;

public class DocumentReader extends FileToStringReader {
	Map<String, String> variables;
	public DocumentReader(SystemResource resource) {
		super(resource, 0);
		variables = new HashMap<String, String>();
	}

	public void addVariable(String variableName, String value)
	{
		variables.put(variableName, value);
	}
	
	@Override
	protected String adjust(String line) {
		String rtnValue = super.adjust(line);
		rtnValue = rtnValue.trim();
		rtnValue = new KeyAndPropertyReplacer(variables).replaceVariables(rtnValue);
		rtnValue = rtnValue + " ";
		return rtnValue;
	}

	public Document readDocument()
	{
		try {
			String document = read();
			return XmlUtil.getDocument(new StringReader(document));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
