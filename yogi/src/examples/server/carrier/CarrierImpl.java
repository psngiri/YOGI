package examples.server.carrier;

import yogi.base.relationship.RelationshipObjectImpl;

public class CarrierImpl extends RelationshipObjectImpl<Carrier> implements Carrier
{
	private String carrierCode;

	protected CarrierImpl(String carrierCode)
	{
		super();
		this.carrierCode = carrierCode;
	}

	public String getName()
	{
		return carrierCode;
	}

	public String getIndex()
	{
		return getCarrierCode();
	}

	@Override
	public String toString()
	{
		return carrierCode;
	}
	
	public String getCarrierCode()
	{
		return carrierCode;
	}
	
	public int compareTo(Carrier carrier)
	{
		if(this == carrier) return 0;
		return carrierCode.compareTo(carrier.getCarrierCode());
	}

}
