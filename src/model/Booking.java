package model;

public class Booking {

    private String bookingID;

    private final String email;

    private final String flightNumber;
    public Booking(String bookingID, String email, String flightNumber) {
        this.bookingID = bookingID;
        this.email = email;
        this.flightNumber = flightNumber;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getEmail() {
        return email;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString() {
        return "Booking Made: " +
                "Booking ID: " + bookingID +
                "Customer: " + email +
                "Flight Number:" + flightNumber ;
    }
}
