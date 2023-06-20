package servises;

import model.*;

import java.time.LocalTime;
import java.util.*;

public class BookingService {

    Map<String, FlightDetails> flights = new HashMap<>();

    public void addFlight(String flightNumber, String airlineName, String destinationFrom, Date departDate, LocalTime departTime,
                          String arrivalCity, LocalTime arrivalTime, PassengerInfo type, CabinClass iClass, double flightPrice) {

        FlightDetails flight = new FlightDetails(flightNumber,
                airlineName,
                new DepartureDetails(destinationFrom, departDate, departTime),
                new ArrivalDetails(arrivalCity, arrivalTime),
                iClass,
                type,
                flightPrice
        );

        flights.put(flightNumber, flight);
    }

    public FlightDetails getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }

    public Collection<FlightDetails> getAllFlights() {
        return flights.values();
    }
}

