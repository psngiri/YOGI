package examples.server.carrier;

import yogi.base.ObjectNotFoundException;
import yogi.base.app.ErrorReporter;
import yogi.base.relationship.index.IndexedManager;

public class CarrierManager extends IndexedManager<Carrier, String>
{
	private static CarrierManager itsInstance = new CarrierManager();
	private static CarrierCreator carrierCreator = new CarrierCreator();
	public static Carrier ANY = new CarrierImpl("any");

	protected CarrierManager()
	{
		super();
	}

	public static CarrierManager get()
	{
		return itsInstance;
	}

	public Carrier getCarrier(String carrierCode)
	{
		if (null == carrierCode) {
			ErrorReporter.get().fine("Could Not Find Carrier ", carrierCode);
			return ANY;
		}
		carrierCode = carrierCode.trim();
		try {
			return this.getObject(carrierCode);
		} catch (ObjectNotFoundException e) {
			return createCarrier(carrierCode);
		}
	}

	private synchronized Carrier createCarrier(String carrierCode)
	{
		try {
			return this.getObject(carrierCode);
		} catch (ObjectNotFoundException e) {
			ErrorReporter.get().info("Creating Carrier ", carrierCode);
			carrierCreator.setCarrierCode(carrierCode);
			return CarrierFactory.get().create(carrierCreator);
		}
	}
}