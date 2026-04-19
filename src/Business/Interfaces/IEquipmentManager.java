package Business.Interfaces;


import java.util.List;


public interface IEquipmentManager {
    IEquipment searchEquipment(int equipmentId);
    void addEquipment(IEquipment equipment);
    void removeEquipment(IEquipment equipment);
    void updateEquipment(IEquipment updatedEquipment);
    boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable);
    IEquipment getEquipmentById(int equipmentId);
    List<IEquipment> getAllEquipment();
    IEquipment searchEquipmentByName(String name);
    void getAvailableEquipment();
}
