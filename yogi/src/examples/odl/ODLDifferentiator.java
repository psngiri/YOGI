package examples.odl;

public class ODLDifferentiator {
	private int position;
	private int size;
	private String value;
	public ODLDifferentiator(String position, String size, String value) {
		super();
		this.position = Integer.valueOf(position);
		this.size = Integer.valueOf(size);
		this.value = value;
	}
	public int getPosition() {
		return position;
	}
	public int getSize() {
		return size;
	}
	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "ODLDifferentiator [position=" + position + ", size=" + size
				+ ", value=" + value + "]";
	}
	
}
