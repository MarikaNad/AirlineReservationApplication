package ui;

import model.*;
import servises.BookingService;
import servises.CustomerService;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdminMenu {

    final BookingService bookingService = new BookingService();
    final CustomerService customerService;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public AdminMenu(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void enterAdminNumber() {
        while (true) {

            printAdminMenu();
            int enterMenu = Console.readNumber("Please select from the following options: ", 1, 5);

            if (enterMenu == 1) {
                seeAllCustomers();
            } else if (enterMenu == 2) {
                displayAvailableFlights();
            } else if (enterMenu == 3) {
                displayUnBooked();
            } else if (enterMenu == 4) {
                addFlightsDestinations();
            } else if (enterMenu == 5) {
                System.out.println("Exiting to the Main Menu!");
                break;
            }
        }
    }

    private void printAdminMenu() {
        System.out.println("------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. Display available flights");
        System.out.println("3. Display Unbooked Flights");
        System.out.println("4. Add a flight destinations and dates");
        System.out.println("5. Back to the main menu");
        System.out.println("------------------------------------------");
    }

    private void seeAllCustomers() {
        System.out.println("Customer Information");
        System.out.println("------------------------------------------");

        if (customerService.getAllCustomers().isEmpty()) {
            System.out.println("Sorry no customers found.");
        } else {
            for (Customer customer : customerService.getAllCustomers()) {
                System.out.println("Customer Name: " + customer.getName());
                System.out.println("Customer Surname: " + customer.getSurname());
                System.out.println("Customer email: " + customer.getEmail());
            }
        }
    }

    private void displayAvailableFlights() {
            System.out.println("Available Flight Information");
            System.out.println("------------------------------------------");
            for (FlightDetails flight : bookingService.getAllFlights()) {
                System.out.println("Flight Number: " + flight.getFlightNumber());
                System.out.println("Airline Name: " + flight.getAirline());
                System.out.println("Departure City: " + flight.getDepartureDetails().getDepartureCity());
                System.out.println("Departure date: " + DATE_FORMAT.format(flight.getDepartureDetails().getDepartureDate()));
                System.out.println("Departure time: " + flight.getDepartureDetails().getDepartureTime());
                System.out.println("Arrival City: " + flight.getArrivalDetails().getArrivalCity());
                System.out.println("Arrival Time: " + flight.getArrivalDetails().getArrivalTime());
                System.out.println("Passenger Type: " + flight.getPassengerInfo());
                System.out.println("Cabin Class: " + flight.getCabinClass());
                System.out.println("Flight Price: " + formatter.format(flight.getFlightPrice()));
                System.out.println("------------------------------------------");
            }
    }
        private void displayUnBooked () {
            throw new RuntimeException("not ready yet");
        }

        private void addFlightsDestinations () {

            while (true) {
                String flightNumber = Console.readText("Please enter flight number:");
                String airlineName = Console.readText("Please enter Airline Name: ");
                String destinationFrom = Console.readText("Please enter Departure City: ");
                String destinationTo = Console.readText("Please enter Arrival City: ");
                Date departDate = Console.readDate("Please enter Departure date ex: dd/mm/yyyy ");
                LocalTime departTime = Console.readTime("Please enter Departure time ex: hh:mm ");
                LocalTime arrivalTime = Console.readTime("Please enter Arrival time ex: hh:mm ");

                int passengerInfo = Console.readNumber("Enter type: 1 for adult , 2 for child ", 1, 2);

                PassengerInfo type;
                if (passengerInfo == 1) {
                    type = PassengerInfo.ADULT;
                } else {
                    type = PassengerInfo.CHILD;
                }

               //TODO add how many seats available for each class and their price difference
                //The Airline Admin should be able to set the total number of seats for each flight.
                int classInfo = Console.readNumber("Enter cabin class: 1 for Economy, 2 for Business, 4 First ", 1, 3);

                CabinClass iClass;
                if (classInfo == 1) {
                    iClass = CabinClass.ECONOMY;
                } else if (classInfo == 2) {
                    iClass = CabinClass.BUSINESS;
                } else {
                    iClass = CabinClass.FIRST;
                }

                double flightPrice = Console.readDouble("Enter flight price: ", 50, 100_000);
                bookingService.addFlight(flightNumber, airlineName, destinationFrom, departDate, departTime,
                        destinationTo, arrivalTime, type, iClass, flightPrice);

                boolean newFlightDestination = Console.readQuery("Would you like to add another Flight Information? y/n ");
                if (!newFlightDestination) {
                    break;
                }
            }
        }
    }
