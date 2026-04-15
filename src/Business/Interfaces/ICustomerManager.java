package Business.Interfaces;

import java.util.List;

public interface ICustomerManager {
    List<ICustomer> searchCustomerByLastName(String lastName);
    void addCustomer(ICustomer customer);
    List<ICustomer> getAllCustomers();
    List<ICustomer> getBannedCustomers();
    boolean updateCustomer(ICustomer updatedCustomer);
    boolean removeCustomerById(ICustomer customer);
    ICustomer getCustomerById(int id);

}
