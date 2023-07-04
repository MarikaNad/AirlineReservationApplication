package ui;

import model.CabinClass;
import model.Customer;
import model.FlightDetails;
import servises.BookingService;
import servises.CustomerService;

import java.util.Date;
import java.util.List;

import static ui.Console.DATE_FORMAT;

public class MainMenu {
    CustomerService customerService = new CustomerService();

    BookingService bookingService = new BookingService();

    private final AdminMenu adminMenu = new AdminMenu(customerService, bookingService);


    public void enterMainMenu() {

        while (true) {
            printMainMenu();
            int enterMenu = Console.readNumber("Please select from the following options: ", 1, 5);

            if (enterMenu == 1) {
                searchAndBookFlight();
            } else if (enterMenu == 2) {
                viewBookedFlights();
            } else if (enterMenu == 3) {
                createAccount();
            } else if (enterMenu == 4) {
                adminMenu.enterAdminNumber();
            } else if (enterMenu == 5) {
                System.out.println("Exiting the application. Thank you!");
                break;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("------------------------------------------");
        System.out.println("1. Find and book a Flight");
        System.out.println("2. View my booked Flights");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("------------------------------------------");
    }

    private void searchAndBookFlight() {

        String departureCity = Console.readText("Please enter Departure City: ");
        String destinationCity = Console.readText("Please enter Arrival City: ");
        Date departureDate = Console.readDate("Please enter Departure date ex: dd/mm/yyyy ");
        boolean returnFlightQuery = Console.readQuery("Would you like to search and book return Flight? y/n ");
        if (returnFlightQuery) {
            Date ReturnDate = Console.readDate("Please enter Return Flight date ex: dd/mm/yyyy ");
        }
        int classSelected = Console.readNumber("Enter cabin class: 1 for Economy, 2 for Business, 3 First ", 1, 3);

        CabinClass chosenClass;
        if (classSelected == 1) {
            chosenClass = CabinClass.ECONOMY;
        } else if (classSelected == 2) {
            chosenClass = CabinClass.BUSINESS;
        } else {
            chosenClass = CabinClass.FIRST;
        }

        // TODO add later number of tickets available and calculate amount per passenger
        // int groupSize = Console.readNumber("Enter how many passengers: (Children under 2 qualify as a lap infant)", 1, 200);


        List<FlightDetails> discoveredFlights = bookingService.findAFlight(departureCity, destinationCity, departureDate, chosenClass);

        if (discoveredFlights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            System.out.println("List of available Departure Flights:");
            System.out.println("------------------------------------------");
            for (FlightDetails available : discoveredFlights) {
                System.out.println("Flight Number: " + available.getFlightNumber());
                System.out.println("Departure City: " + available.getDepartureDetails().getDepartureCity());
                System.out.println("Arrival City: " + available.getArrivalDetails().getArrivalCity());
                System.out.println("Departure Date: " + DATE_FORMAT.format(available.getDepartureDetails().getDepartureDate()));
                System.out.println("Cabin Class: " + available.getCabinClass());
                System.out.println("Flight price: " + available.getFlightPrice());
                System.out.println("------------------------------------------");
            }
        }

        while (true) {
            boolean bookFlight = Console.readQuery("Would you like to book a flight? y/n ");
            if (bookFlight) {
                boolean hasAccount = Console.readQuery("Do you have an Account with us? y/n ");
                if (hasAccount) {
                    String email = Console.readEmail("Enter your email please, format: name@domain.com ");

                    // TODO maybe this is better than just `customerService.doesCustomerExist(email)`?
                    Customer custom = customerService.getCustomer(email);

                    if (custom != null) {

                        String selectedFlightNumber = Console.readText("Please enter flight number: ");
                        FlightDetails selectedFlight = containsFlight(discoveredFlights, selectedFlightNumber);

                        if (selectedFlight == null) {
                            System.out.println("This Flight Number is not available. Please enter a valid Flight number.");
                        } else {
                            // Perform the booking with the selected flight
                            // TODO: Implement the booking logic
                            break;
                        }
                    } else {
                        System.out.println("Please go back and create an Account");
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }


    private FlightDetails containsFlight(List<FlightDetails> discoveredFlights, String selectedFlightNumber) {
        for (FlightDetails selectedAvailableFlight : discoveredFlights) {
            if (selectedAvailableFlight.getFlightNumber().equals(selectedFlightNumber)) {
                return selectedAvailableFlight;
            }
        }
        return null;
    }

    private void viewBookedFlights() {
        throw new RuntimeException("not ready yet");
    }

    private void createAccount() {
        while (true) {
            String firstName = Console.readText("Please enter your First Name: ");

            String lastName = Console.readText("Please enter your Last Name: ");

            String email = Console.readEmail("Please enter your email address: ");

            customerService.addCustomer(firstName, lastName, email);

            boolean newAccount = Console.readQuery("Would you like to create another Account? y/n ");
            if (!newAccount) {
                break;
            }
        }
    }
}