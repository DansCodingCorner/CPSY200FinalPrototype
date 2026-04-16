package Business;

import java.util.ArrayList;
import java.util.List;

import Business.Interfaces.ICustomer;
import Business.Interfaces.ICustomerManager;
import Persistence.ICustomerDataAccess;
import Persistence.CustomerDataAccess;

public class CustomerManager implements ICustomerManager {
    
    private ICustomerDataAccess customerDataAccess;

    public CustomerManager() {
        customerDataAccess = CustomerDataAccess.getInstance();
    }

    public List<ICustomer> searchCustomerByLastName(String lastName) {
        List<ICustomer> results = new ArrayList<>();

        for (ICustomer customer : customerDataAccess.getCustomerList()) {
            if (customer.getLastName().equalsIgnoreCase(lastName)) {
                results.add(customer);
            }
        }

        return results;
    }

    @Override
    public ICustomer getCustomerById(int id) {
        for (ICustomer customer : customerDataAccess.getCustomerList()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public List<ICustomer> getBannedCustomers() {
        List<ICustomer> bannedCustomers = new ArrayList<>();

        for (ICustomer customer : customerDataAccess.getCustomerList()) {
            if (customer.isBanned()) {
                bannedCustomers.add(customer);
            }
        }

        return bannedCustomers;
    }

    @Override
    public void addCustomer(ICustomer customer) {
        customerDataAccess.getCustomerList().add(customer);
        customerDataAccess.saveCustomerList(customerDataAccess.getCustomerList());
    }

    @Override
    public List<ICustomer> getAllCustomers() {
        return customerDataAccess.getCustomerList();
    }

    public boolean updateCustomer(ICustomer updatedCustomer) {
        List<ICustomer> customers = customerDataAccess.getCustomerList();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer);
                customerDataAccess.saveCustomerList(customers);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeCustomerById(ICustomer customer) {
        List<ICustomer> customers = customerDataAccess.getCustomerList();

        boolean removed = customers.remove(customer);

        if (removed) {
            customerDataAccess.saveCustomerList(customers);
        }

        return removed;
    }
}