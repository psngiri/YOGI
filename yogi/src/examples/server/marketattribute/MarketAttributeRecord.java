package examples.server.marketattribute;

import java.io.Serializable;


public class MarketAttributeRecord implements Serializable {

	private static final long serialVersionUID = 5228218866791067902L;
	
	private int action;
	private long timeStamp;
	private String modifiedByUserId;
	private String market;
	private String attribute;
	
	public MarketAttributeRecord(MarketAttribute marketAttribute) {
		super();
		this.action = marketAttribute.getAction().getValue();
		this.timeStamp = marketAttribute.getTimeStamp();
		this.modifiedByUserId = marketAttribute.getModifiedByUser().getId();
		this.market = marketAttribute.getKey().getMarket();		
		this.attribute = marketAttribute.getAttribute();
	}

	public int getAction() {
		return action;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getModifiedByUserId() {
		return modifiedByUserId;
	}

	public String getMarket() {
		return market;
	}

	public String getAttribute() {
		return attribute;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + action;
		result = prime * result
				+ ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime
				* result
				+ ((modifiedByUserId == null) ? 0 : modifiedByUserId.hashCode());
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketAttributeRecord other = (MarketAttributeRecord) obj;
		if (action != other.action)
			return false;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (modifiedByUserId == null) {
			if (other.modifiedByUserId != null)
				return false;
		} else if (!modifiedByUserId.equals(other.modifiedByUserId))
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarketAttributeRecord [action=" + action + ", timeStamp="
				+ timeStamp + ", modifiedByUserId=" + modifiedByUserId
				+ ", market=" + market + ", attribute=" + attribute + "]";
	}
	
}
