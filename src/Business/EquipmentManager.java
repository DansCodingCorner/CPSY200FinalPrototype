package Business;

import java.util.List;

import Business.Interfaces.IEquipment;
import Persistence.EquipmentDataAccess;
import Persistence.IEquipmentDataAccess;

import java.util.ArrayList;

public class EquipmentManager {
    private ArrayList<Equipment> equipmentList;
    public IEquipmentDataAccess dataAccess = EquipmentDataAccess.getInstance();

    public EquipmentManager() {
        equipmentList = dataAccess.loadEquipmentList();
    }

    public Equipment searchEquipment(int equipmentId) {
        for (Equipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                return e;
            }
        }
        return null;
    }
    public ArrayList<Equipment> loadEquipmentList() {
        return equipmentList;
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
    }


    public void updateEquipment(Equipment updatedEquipment) {
        for (int i = 0; i < equipmentList.size(); i++) {
            if (equipmentList.get(i).getId() == updatedEquipment.getId()) {
                equipmentList.set(i, updatedEquipment);
                return;
            }
        }
    }
}
// this is a comment 