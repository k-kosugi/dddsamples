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
public class CustomerApplicationService {

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

        if (customerFirstName != null) {
            customer.setCustomerFirstName(customerFirstName);
        }

        if (customerLastName != null) {
            customer.setCustomerLastName(customerLastName);
        }

        if (streetAddress1 != null) {
            customer.setStreetAddress1(streetAddress1);
        }

        if (streetAddress2 != null) {
            customer.setStreetAddress2(streetAddress2);
        }

        if (city != null) {
            customer.setCity(city);
        }

        if (stateOrProvince != null) {
            customer.setStateOrProvince(stateOrProvince);
        }

        if (postalCode != null) {
            customer.setPostalCode(postalCode);
        }

        if (country != null) {
            customer.setCountry(country);
        }

        if (homePhone != null) {
            customer.setHomePhone(homePhone);
        }

        if (mobilePhone != null) {
            customer.setMobilePhone(mobilePhone);
        }

        if (primaryEmailAddress != null) {
            customer.setPrimaryEmailAddress(primaryEmailAddress);
        }

        if (secondaryEmailAddress != null) {
            customer.setSecondaryEmailAddress(secondaryEmailAddress);
        }

        customerDao.saveCustomer(customer);
    }

    @GET
    @Path("all")
    public List<Customer> all() {
        return customerDao.all();
    }

}
