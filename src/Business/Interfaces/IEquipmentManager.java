package Business.Interfaces;


import Business.Equipment;
public interface IEquipmentManager {
    public Equipment searchEquipment(int equipmentId);
    public void addEquipment(Equipment equipment);
    public void removeEquipment(Equipment equipment);
    public void updateEquipment(Equipment updatedEquipment);
    public boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable);
    public Equipment getEquipmentById(int equipmentId);
}
