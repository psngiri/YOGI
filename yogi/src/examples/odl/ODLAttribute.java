package examples.odl;

import yogi.base.util.range.Range;

public class ODLAttribute {
	private ODLType type;
	private String name;
	private int position;
	private int size;
	private int decimal;
	
	public ODLAttribute(String type, String name, String position, String size) {
		super();
		this.type = ODLType.valueOf(type.toUpperCase());
		this.name = name;
		this.position = Integer.valueOf(position);
		this.size = Integer.valueOf(size);
	}
	
	public ODLAttribute(String type, String name, String position, String size, String decimal) {
		this(type, name, position, size);
		this.decimal = Integer.valueOf(decimal);
	}
	
	public ODLType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getPosition() {
		return position;
	}
	public int getSize() {
		return size;
	}
	public int getDecimal() {
		return decimal;
	}

	public Range<Integer> getRange(){
		return new Range<Integer>(position, position+size);
	}
	@Override
	public String toString() {
		return "Attribute [type=" + type + ", name=" + name + ", position="
				+ position + ", size=" + size + ", decimal=" + decimal + "]";
	}
	
}
