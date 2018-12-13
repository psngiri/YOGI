package examples.flight;


import yogi.base.validation.ValidatorAdapter;

public class FlightValidator extends ValidatorAdapter<Flight> {

	@Override
	public boolean validate(Flight object) {
		if(object.getArrivalAirport() == null) return false;
		if(object.getDepartureAirport() == null) return false;
		return true;
	}

	@Override
	public boolean validate() {
//		List<Flight> flights = FlightManager.get().findAll();
		//check for duplicates and remove the duplicates here
		return super.validate();
	}

}
