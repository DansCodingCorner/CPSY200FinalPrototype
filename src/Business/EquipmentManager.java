package Business;

import java.util.List;

import Business.Interfaces.IEquipment;
import Business.Interfaces.IEquipmentManager;
import Persistence.EquipmentDataAccess;
import Persistence.IEquipmentDataAccess;
import java.util.ArrayList;

public class EquipmentManager implements IEquipmentManager
{
    public IEquipmentDataAccess dataAccess = EquipmentDataAccess.getInstance();

    public EquipmentManager() 
    {
    	 this.dataAccess = EquipmentDataAccess.getInstance();
    }

    @Override
    public IEquipment searchEquipment(int equipmentId) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
    	
        for (IEquipment e : equipmentList) {
            if (e.getId() == equipmentId) 
            {
                return e;
            }
        }
        
        return null;
    }
    @Override
    public List<IEquipment> loadEquipmentList() {
        return dataAccess.loadEquipmentList();
    }

    @Override
    public void addEquipment(IEquipment equipment) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
    	
    	equipmentList.add(equipment);
    	
    	dataAccess.saveEquipmentList(equipmentList);
    }

    @Override
    public void removeEquipment(IEquipment equipment) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
    	
    	equipmentList.removeIf(e -> e.getId() == equipment.getId());
    	
    	dataAccess.saveEquipmentList(equipmentList);
    }

    @Override
    public void updateEquipment(IEquipment updatedEquipment)
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

        for (int i = 0; i < equipmentList.size(); i++) {
            if (equipmentList.get(i).getId() == updatedEquipment.getId()) {
            	equipmentList.set(i, updatedEquipment);
                dataAccess.saveEquipmentList(equipmentList);
                return;
            }
        }
    }

    @Override
    public boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable)
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

        for (IEquipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                e.setAvailable(isAvailable);
                dataAccess.saveEquipmentList(equipmentList);
                return true;
            }
        }
        return false;
    }
    @Override
    public IEquipment getEquipmentById(int equipmentId) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

        for (IEquipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                return e;
            }
        }
        System.out.println("Equipment not found.");
        return null;
    }

    @Override
    public List<IEquipment> getAllEquipment() 
    {
        return dataAccess.loadEquipmentList();
    }

    @Override
    public IEquipment searchEquipmentByName(String name) {
        List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
        for (IEquipment equipment : equipmentList) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        System.out.println("Equipment not found.");
        return null;
    }

    /**@param none
     * @return none
     *Used to display all the equipment that is currently available for rent to the user. Called in the UI manager class when the user selects the option to view available equipment.
     */
    @Override
    public void getAvailableEquipment() {
            List<IEquipment> availableEquipment = new ArrayList<>();
            List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
            for (IEquipment equipment : equipmentList) {
                if (equipment.isAvailable()) {
                    availableEquipment.add(equipment);
                }
            }
            if (availableEquipment.isEmpty()) {
                System.out.println("No equipment is currently available.");
            } else {
                System.out.println("Available Equipment:");
                for (IEquipment equipment : availableEquipment) {
                    System.out.println(equipment);
                }
            }
    }

    @Override
    public int getNextEquipmentId() {
        List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
        int id = 101; // Starting ID
        while (true) {
            boolean exists = false;
            for (IEquipment equipment : equipmentList) {
                if (equipment.getId() == id) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                return id;
            }
            id+= 100; // Increment by 100 for the next ID
        }
    }

    @Override
    public int getCategoryById(int categoryId) {
        List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
        for (IEquipment equipment : equipmentList) {
            if (equipment.getCategoryId() == categoryId) {
                return equipment.getCategoryId();
            }
        }
        System.out.println("Category not found.");
        return -1; // Return -1 to indicate category not found
    }

}
