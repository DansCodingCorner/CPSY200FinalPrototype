package Business;

import java.util.ArrayList;

import Business.Interfaces.IEquipmentManager;

public class EquipmentManager implements IEquipmentManager {
    private ArrayList<Equipment> equipmentList;

    public EquipmentManager() {
        equipmentList = new ArrayList<>();
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

    public boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable) {
        for (Equipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                e.setAvailable(isAvailable);
                return true;
            }
        }
        return false;
    }

    public Equipment getEquipmentById(int equipmentId) {
        for (Equipment e : equipmentList) {
            if (e.getId() == equipmentId) {
                return e;
            }
        }
        System.out.println("Equipment not found.");
        return null;
    }
}
