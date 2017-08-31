package repository;

import java.util.List;

import model.Customer;

public interface Repository {

    Customer addCustomer(Customer customer);

    void getGustomerById(int id);

    List<Customer> getAllCutomers();

    void removeCustomer(int id);
    
}
