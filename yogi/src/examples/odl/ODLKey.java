package examples.odl;

public class ODLKey {
	private String attribute;

	public ODLKey(String attribute) {
		super();
		this.attribute = attribute;
	}

	public String getAttribute() {
		return attribute;
	}

	@Override
	public String toString() {
		return "ODLKey [attribute=" + attribute + "]";
	}
	
}
