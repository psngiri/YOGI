package yogi.report.rdl;

public class RdlColumn {
	private RdlType type;
	private String name;
	private int size;
	
	public RdlColumn(RdlType type, String name, int size) {
		super();
		this.type = type;
		this.name = name;
		this.size = size;
	}
	
	public RdlType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "RdlColumn [type=" + type + ", name=" + name + ", size=" + size + "]";
	}

	
}
