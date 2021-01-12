package com.redhat.japan.ddd.application_service;

import com.redhat.japan.ddd.infrastructure.CustomerDao;
import com.redhat.japan.ddd.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerResource {

    @Inject
    private CustomerDao customerDao;

    @POST
    @Path("save")
    @Transactional
    public void saveCustomer(
            @QueryParam("id") String customerId,
            @QueryParam("first") String customerFirstName, @QueryParam("last") String customerLastName,
            @QueryParam("street1") String streetAddress1, @QueryParam("street2") String streetAddress2,
            @QueryParam("city") String city, @QueryParam("state") String stateOrProvince,
            @QueryParam("postalCode") String postalCode, @QueryParam("country") String country,
            @QueryParam("phone1") String homePhone, @QueryParam("phone2") String mobilePhone,
            @QueryParam("email1") String primaryEmailAddress, @QueryParam("email2") String secondaryEmailAddress) {

        Customer customer = customerDao.readCustomer(customerId);

        if (customer == null) {
            customer = new Customer();
            customer.setCustomerId(customerId);
        }

        customer.setCustomerFirstName(customerFirstName);
        customer.setCustomerLastName(customerLastName);
        customer.setStreetAddress1(streetAddress1);
        customer.setStreetAddress2(streetAddress2);
        customer.setCity(city);
        customer.setStateOrProvince(stateOrProvince);
        customer.setPostalCode(postalCode);
        customer.setHomePhone(homePhone);
        customer.setMobilePhone(mobilePhone);
        customer.setPrimaryEmailAddress(primaryEmailAddress);
        customer.setSecondaryEmailAddress(secondaryEmailAddress);

        customerDao.saveCustomer(customer);
    }

    @GET
    @Path("all")
    public List<Customer> all() {
        return customerDao.all();
    }

}
