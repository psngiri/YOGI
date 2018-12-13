package examples.server.sub;

import yogi.base.Creator;

public class SubCreator implements Creator<Sub> {
	private int subNumber;
	
	public Sub create() {
		return new SubImpl(subNumber);
	}

	public int getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(int subNumber) {
		this.subNumber = subNumber;
	}

	@Override
	public String toString() {
		return String.valueOf(subNumber);
	}
		
}
