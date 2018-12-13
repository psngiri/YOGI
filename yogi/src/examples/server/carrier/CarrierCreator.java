package examples.server.carrier;

import yogi.base.Creator;

public class CarrierCreator implements Creator<Carrier>
{
	private String carrierCode;

	public Carrier create()
	{
		return new CarrierImpl(carrierCode);
	}

	public String getCarrierCode()
	{
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode)
	{
		this.carrierCode = carrierCode;
	}
}