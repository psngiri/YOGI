package examples.airport;

import yogi.base.relationship.RelationshipObjectImpl;

public class BaseAirportImpl<T extends Airport> extends RelationshipObjectImpl<T> implements Airport {
	private String code;
	
	protected BaseAirportImpl(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String getName()
	{
		return code;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getIndex() {
		return getCode();
	}

	@Override
	public int compareTo(Airport o) {
		return code.compareTo(o.getCode());
	}
	
}
