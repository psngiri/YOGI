package examples.server.carrier;

import yogi.base.Factory;

public class CarrierFactory extends Factory<Carrier>
{
	private static CarrierFactory itsInstance = new CarrierFactory(CarrierManager.get());

	protected CarrierFactory(CarrierManager manager)
	{
		super(manager);
	}

	public static CarrierFactory get()
	{
		return itsInstance;
	}
}
