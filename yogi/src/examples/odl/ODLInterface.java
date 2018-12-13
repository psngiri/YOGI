package examples.odl;

import java.util.LinkedHashMap;

public class ODLInterface {
	private String interfaceName;
	private ODLDifferentiator differentiator;
	private ODLKey key;
	private LinkedHashMap<String, ODLAttribute> attributes;
	
	public ODLInterface(String interfaceName) {
		super();
		this.interfaceName = interfaceName;
		attributes = new LinkedHashMap<String, ODLAttribute>();
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public LinkedHashMap<String, ODLAttribute> getAttributes() {
		return attributes;
	}

	public ODLDifferentiator getDifferentiator() {
		return differentiator;
	}

	public ODLKey getKey() {
		return key;
	}

	void setDifferentiator(ODLDifferentiator differentiator) {
		this.differentiator = differentiator;
	}

	void setKey(ODLKey key) {
		this.key = key;
	}
	
}
