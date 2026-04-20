package Business;

import java.util.ArrayList;
import java.util.List;

import Business.Interfaces.IEquipmentManager;

public class EquipmentManager implements IEquipmentManager{
    private ArrayList<Equipment> equipmentList;

    public EquipmentManager() {
        equipmentList = new ArrayList<>();
    }

    public Equipment searchEquipment(int equipmentId) {
        for (Equipment e : equipmentList) {
            if (e.getEquipmentId() == equipmentId) {
                return e;
            }
        }
        return null;
    }
    public List<Equipment> loadEquipmentList() {
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
            if (equipmentList.get(i).getEquipmentId() == updatedEquipment.getEquipmentId()) {
                equipmentList.set(i, updatedEquipment);
                return;
            }
        }
    }
}
// this is a comment 