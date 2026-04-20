package Business.Interfaces;


import java.util.List;

import Business.Equipment;

public interface IEquipmentManager {
    Equipment searchEquipment(int equipmentId);
    List<Equipment> loadEquipmentList();
    void addEquipment(Equipment equipment);
    void removeEquipment(Equipment equipment);
    void updateEquipment(Equipment updatedEquipment);
}