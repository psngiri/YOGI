package examples.server.sub;

import yogi.base.relationship.RelationshipObjectImpl;

public class SubImpl extends RelationshipObjectImpl<Sub> implements Sub {
	
	private int subNumber;

	protected SubImpl(int subNumber) {
		super();
		this.subNumber=subNumber;
	}

	@Override
	public Integer getIndex() {
		return subNumber;
	}
	
	public String getName() {
		return String.valueOf(subNumber);
	}
	
	@Override
	public String toString() {
		return String.valueOf(subNumber);
	}

	@Override
	public int getSubNumber() {
		return subNumber;
	}

	@Override
	public int compareTo(Sub o) {
		if(this==o) return 0;
		return this.subNumber - o.getSubNumber();
	}
	
}
