package servises;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CustomerService {
    HashMap<String, Customer> customers = new HashMap<>();

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
//        customers.add(customer);
        customers.put(email, customer);
    }

    public Customer getCustomer(String email) {
        return customers.get(email);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
//        return customers;
    }

//    //checking that  email exists in hashmap, make it public to get access in main menu
//    public boolean doesCustomerExist(String email) {
//        return customers.containsKey(email);
//    }

}
