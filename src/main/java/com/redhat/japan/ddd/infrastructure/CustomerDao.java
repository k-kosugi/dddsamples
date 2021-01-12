package com.redhat.japan.ddd.infrastructure;

import com.redhat.japan.ddd.model.Customer;

import java.util.List;

public interface CustomerDao {

    Customer readCustomer(String customerId);

    void saveCustomer(Customer customer);

    List<Customer> all();

}
