package Business.Interfaces;


import java.util.List;
import Business.Equipment;


public interface IEquipmentManager {
    IEquipment searchEquipment(int equipmentId);
    List<IEquipment> loadEquipmentList();
    void addEquipment(IEquipment equipment);
    void removeEquipment(IEquipment equipment);
    void updateEquipment(IEquipment updatedEquipment);
    int getNextEquipmentId();
    int getCategoryById(int categoryId);
    void getAvailableEquipment();
    IEquipment searchEquipmentByName(String name);
    List<IEquipment> getAllEquipment();
    boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable);
    IEquipment getEquipmentById(int equipmentId);

}
