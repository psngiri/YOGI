package examples.odl;

import java.util.LinkedHashMap;

public class ODL {
	private LinkedHashMap<String, ODLInterface> interfaces;

	public ODL() {
		super();
		this.interfaces = new LinkedHashMap<String, ODLInterface>();
	}

	public LinkedHashMap<String, ODLInterface> getInterfaces() {
		return interfaces;
	}
	
}
