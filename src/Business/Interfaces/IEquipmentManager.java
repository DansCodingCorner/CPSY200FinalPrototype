package Business.Interfaces;


import java.util.ArrayList;

import Business.Equipment;
public interface IEquipmentManager {
    Equipment searchEquipment(int equipmentId);
    void addEquipment(Equipment equipment);
    void removeEquipment(Equipment equipment);
    void updateEquipment(Equipment updatedEquipment);
    boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable);
    Equipment getEquipmentById(int equipmentId);
    ArrayList<Equipment> getAllEquipment();
    Equipment searchEquipmentByName(String name);
    void getAvailableEquipment();
}
