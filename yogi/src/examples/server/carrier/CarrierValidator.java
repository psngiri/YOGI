package examples.server.carrier;

import yogi.base.validation.ValidatorAdapter;

public class CarrierValidator extends ValidatorAdapter<Carrier>
{
	@Override
	public boolean validate(Carrier carrier)
	{
		if (carrier.getCarrierCode().length() < 2) {
			return false;
		}
		return true;
	}
}
