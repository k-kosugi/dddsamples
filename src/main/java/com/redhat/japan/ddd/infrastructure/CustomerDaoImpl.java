package com.redhat.japan.ddd.infrastructure;

import com.redhat.japan.ddd.model.Customer;
import io.quarkus.hibernate.orm.PersistenceUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao {

    @Inject
    private EntityManager entityManager;

    public Customer readCustomer(String customerId) {
        return this.entityManager.find(Customer.class, customerId);
    }

    public void saveCustomer(Customer customer) {
        this.entityManager.persist(customer);
    }

    public List<Customer> all() {
        return this.entityManager.createNamedQuery("Customer", Customer.class)
                .getResultList();
    }
}
