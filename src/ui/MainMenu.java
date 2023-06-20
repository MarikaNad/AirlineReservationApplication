package ui;

import model.CabinClass;
import servises.CustomerService;

import java.util.Date;

public class MainMenu {

    CustomerService customerService = new CustomerService();

    private final AdminMenu adminMenu = new AdminMenu(customerService);

    public void enterMainMenu() {

        while (true) {
            printMainMenu();
            int enterMenu = Console.readNumber("Please select from the following options: ", 1, 5);

            if (enterMenu == 1) {
                searchBookFlight();
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

    private void searchBookFlight() {

        String departureCity = Console.readText("Please enter Departure City: ");
        String destinationCity = Console.readText("Please enter Arrival City: ");
        Date departureDate = Console.readDate("Please enter Departure date ex: dd/mm/yyyy ");
        boolean returnFlightQuery = Console.readQuery("Would you like to search and book return Flight? y/n ");
        if (returnFlightQuery) {
            Date ReturnDate = Console.readDate("Please enter Return Flight date ex: dd/mm/yyyy ");
        }
        int classSelected = Console.readNumber("Enter cabin class: 1 for Economy, 2 for Business, 4 First ", 1, 3);

        CabinClass chosenClass;
        if (classSelected == 1) {
            chosenClass = CabinClass.ECONOMY;
        } else if (classSelected == 2) {
            chosenClass = CabinClass.BUSINESS;
        } else {
            chosenClass = CabinClass.FIRST;
        }

        int groupSize = Console.readNumber("Enter how many passengers: (Children under 2 qualify as a lap infant)", 1, 200);
        // todo display found flights

        // would you like to book this flight? y/n - no exit to the menu
        // yes - do you have an account? n - send to create an account
        // yes - enter your email address

        while (true) {
            boolean bookFlight = Console.readQuery("Would you like to book a flight? y/n ");
            if (bookFlight) {
                boolean isYes = Console.readQuery("Do you have an Account with us? y/n ");
                if (isYes) {
                    String email = Console.readEmail("Enter your email please, format: name@domain.com ");
                } else {
                    System.out.println("Please go back and create an Account");
                    break;
                }
            } else {
                break;
            }
        }



        // display flight with total price - add option to confirm or cancel
    }

        private void viewBookedFlights () {
            throw new RuntimeException("not ready yet");
        }

        private void createAccount () {
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