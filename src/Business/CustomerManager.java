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

        if (bannedCustomers.isEmpty()) {
            System.out.println("No banned customers found.");
        }

        return bannedCustomers;
    }

    @Override
    public void addCustomer(ICustomer customer) {
            
            customerDataAccess.getCustomerList().add(customer);
            customerDataAccess.saveCustomerList(customerDataAccess.getCustomerList());
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " added successfully!");
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
                System.out.println("Customer " + updatedCustomer.getFirstName() + " " + updatedCustomer.getLastName() + " updated successfully!");
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
                System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " removed successfully!");
        }

        return removed;
    }

    @Override
    public void banCustomerById(int id) {
        List<ICustomer> customers = customerDataAccess.getCustomerList();

        for (ICustomer customer : customers) {
            if (customer.getId() == id) {
                customer.setBanned(true);
                customerDataAccess.saveCustomerList(customers);
                System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been banned.");
                return;
            }
        }
    }

    @Override
    public void unbanCustomerById(int id) {
        List<ICustomer> customers = customerDataAccess.getCustomerList();

        for (ICustomer customer : customers) {
            if (customer.getId() == id) {
                customer.setBanned(false);
                customerDataAccess.saveCustomerList(customers);
                System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " has been unbanned.");
                return;

            }
        }
    }

    @Override
    public int getNextCustomerId() {
        List<ICustomer> customers = customerDataAccess.getCustomerList();
        int maxId = 1000; // Start from 1001
        for (ICustomer customer : customers) {
            if (customer.getId() > maxId) {
                maxId = customer.getId();
            }
        }
        return maxId + 1;
    }

}