package model;

import java.time.LocalTime;
import java.util.Date;

public class FlightDetails {

    private String flightNumber;

    private String airline;

    private DepartureDetails departureDetails;

    private ArrivalDetails arrivalDetails;
    private CabinClass cabinClass;

    private PassengerInfo passengerInfo;

    private double flightPrice;



    public FlightDetails(String flightNumber, String airline, DepartureDetails departureDetails,
                         ArrivalDetails arrivalDetails, CabinClass cabinClass, PassengerInfo passengerInfo, double flightPrice) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDetails = departureDetails;
        this.arrivalDetails = arrivalDetails;
        this.cabinClass = cabinClass;
        this.passengerInfo = passengerInfo;
        this.flightPrice = flightPrice;
    }


    public ArrivalDetails getArrivalDetails() {
        return arrivalDetails;
    }

    public DepartureDetails getDepartureDetails() {
        return departureDetails;
    }

    public CabinClass getCabinClass() {
        return cabinClass;
    }

    public PassengerInfo getPassengerInfo() {
        return passengerInfo;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }


    @Override
    public String toString() {
        return "FlightDetails{" +
                "flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", departureDetails=" + departureDetails +
                ", arrivalDetails=" + arrivalDetails +
                ", cabinClass=" + cabinClass +
                ", passengerInfo=" + passengerInfo +
                ", flightPrice=" + flightPrice +
                '}';
    }
}