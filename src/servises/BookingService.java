package servises;

import model.*;

import java.time.LocalTime;
import java.util.*;

public class BookingService {
    Map<String, FlightDetails> flights = new HashMap<>();
    Map<String, Booking> bookedFlights = new HashMap<>();

    public void addFlight(String flightNumber, String airlineName, String departureCity, Date departDate, LocalTime departTime,
                          String arrivalCity, LocalTime arrivalTime, PassengerInfo type, CabinClass iClass, double flightPrice) {

        FlightDetails flight = new FlightDetails(flightNumber,
                airlineName,
                new DepartureDetails(departureCity, departDate, departTime),
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

    public List<FlightDetails> findAFlight(String departureCity, String arrivalCity, Date departDate, CabinClass chosenClass) {
        List<FlightDetails> availableFlights = new ArrayList<FlightDetails>();

        for (FlightDetails searchedFlight : flights.values()) {
            // if f is suitable, we store it for later (together with the other suitable ones)

            if (searchedFlight.getDepartureDetails().getDepartureCity().equals(departureCity)
                    && searchedFlight.getArrivalDetails().getArrivalCity().equals(arrivalCity)
                    && searchedFlight.getDepartureDetails().getDepartureDate().equals(departDate)
                    && searchedFlight.getCabinClass().equals(chosenClass)) {

                availableFlights.add(searchedFlight);
            }
        }

        return availableFlights;
    }

    public boolean bookAFlight(String email, String flightNumber) {
        String bookingID = UUID.randomUUID().toString();

        Booking booking = new Booking(bookingID, email, flightNumber);

        bookedFlights.put(bookingID, booking);

        boolean bookingSuccessful = true;
        return bookingSuccessful;
    }

    public Collection<Booking> getAllBookings() {
        return bookedFlights.values();
    }

//  this method is not working because flight details has flight number AND Booked flights automatically generates different ID
//    public List<FlightDetails> findUnBookedFlights () {
//
//        List<FlightDetails> notBookedFlights = new ArrayList<>();
//
//        for (FlightDetails free : flights.values()) {
//            if(!bookedFlights.containsKey(free.getFlightNumber())) {
//                notBookedFlights.add(free);
//            }
//        }
//        return notBookedFlights;
//    }

    public List<FlightDetails> findUnBookedFlights() {

        List<FlightDetails> notBookedFlights = new ArrayList<>();

        for (FlightDetails flight : flights.values()) {
            boolean isBooked = false;
            for (Booking booking : bookedFlights.values()) {
                if (booking.getFlightNumber().equals(flight.getFlightNumber())) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                notBookedFlights.add(flight);
            }
        }
        return notBookedFlights;
    }
}


