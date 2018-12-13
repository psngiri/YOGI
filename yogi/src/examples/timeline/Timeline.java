package examples.timeline;

import java.util.Collection;

import yogi.base.UniqueName;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;

import examples.airport.Airport;
import examples.flight.Flight;


public interface Timeline extends RelationshipObject, UniqueName {
  Airport getAirport();
  ImmutableList<Flight> getArrivingFlights();
  ImmutableList<Flight> getDepartingFlights();
  void addArrivingFlight(Flight flight);
  void addAllArrivingFlights(Collection<? extends Flight> flights);
  void addDepartingFlight(Flight flight);
  void addAllDepartingFlights(Collection<? extends Flight> flights);
}
