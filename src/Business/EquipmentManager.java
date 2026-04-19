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
    
    public List<IEquipment> loadEquipmentList() {
        return dataAccess.loadEquipmentList();
    }

    public void addEquipment(Equipment equipment) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
    	
    	equipmentList.add(equipment);
    	
    	dataAccess.saveEquipmentList(equipmentList);
    }

    public void removeEquipment(Equipment equipment) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

    	equipmentList.remove(equipment);
    	
    	dataAccess.saveEquipmentList(equipmentList);
    }


    public void updateEquipment(Equipment updatedEquipment)
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

        for (int i = 0; i < equipmentList.size(); i++) {
            if (equipmentList.get(i).getId() == updatedEquipment.getId()) {
            	equipmentList.set(i, updatedEquipment);
                return;
            }
        }
    }


    public boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable)
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();

        for (IEquipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                e.setAvailable(isAvailable);
                return true;
            }
        }
        return false;
    }

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
    public Equipment searchEquipmentByName(String name) {
        List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
        for (IEquipment equipment : equipmentList) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return (Equipment) equipment;
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
            List<Equipment> availableEquipment = new ArrayList<>();
            List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
            for (IEquipment equipment : equipmentList) {
                if (equipment.isAvailable()) {
                    availableEquipment.add((Equipment) equipment);
                }
            }
            if (availableEquipment.isEmpty()) {
                System.out.println("No equipment is currently available.");
            } else {
                System.out.println("Available Equipment:");
                for (Equipment equipment : availableEquipment) {
                    System.out.println(equipment);
                }
            }
    }


	@Override
	public void addEquipment(IEquipment equipment) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeEquipment(IEquipment equipment) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateEquipment(IEquipment updatedEquipment) {
		// TODO Auto-generated method stub
		
	}

}
