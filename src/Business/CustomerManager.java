package Business;

import java.util.ArrayList;
import java.util.List;

import Business.Interfaces.ICustomer;
import Business.Interfaces.ICustomerManager;


public class CustomerManager implements ICustomerManager {
    
    private List<ICustomer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();

    }

    public List<ICustomer> searchCustomerByLastName(String lastName) {
        List<ICustomer> results = new ArrayList<>();
        for (ICustomer customer : customers) {
            if (customer.getLastName().equalsIgnoreCase(lastName)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ICustomer getCustomerById(int id) {
        for (ICustomer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public List<ICustomer> getBannedCustomers() {
        List<ICustomer> bannedCustomers = new ArrayList<>();
        for (ICustomer customer : customers) {
            if (customer.isBanned()) {
                bannedCustomers.add(customer);
            }
        }
        return bannedCustomers;
    }

    @Override
    public void addCustomer(ICustomer customer) {
        customers.add(customer);
    }

    @Override
    public List<ICustomer> getAllCustomers() {
        return customers;
    }



    public boolean updateCustomer(ICustomer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer);
                return true;
            }
        }
        return false;
    }

    public boolean removeCustomerById(ICustomer customer) {
        return customers.removeIf(c -> c.getId() == customer.getId());
    }
}
