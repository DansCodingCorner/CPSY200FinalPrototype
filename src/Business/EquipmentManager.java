package Business;

import java.util.List;
import Business.Interfaces.IEquipment;
import Persistence.EquipmentDataAccess;
import Persistence.IEquipmentDataAccess;
import java.util.ArrayList;

public class EquipmentManager implements IEquipmentManager
{
    public IEquipmentDataAccess dataAccess = EquipmentDataAccess.getInstance();

    public EquipmentManager() 
    {
    	IEquipmentDataAccess dataAccess = EquipmentDataAccess.getInstance();
    }


    public IEquipment searchEquipment(int equipmentId) 
    {
    	List<IEquipment> equipmentList = dataAccess.loadEquipmentList();
    	
        for (IEquipment e : equipmentList) {
            if (e.getId() == equipmentId) {
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
}
