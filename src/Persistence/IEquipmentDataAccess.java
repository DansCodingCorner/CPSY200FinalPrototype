package Persistence;

import java.util.List;

import Business.Interfaces.IEquipment;

public interface IEquipmentDataAccess 
{
	public List<IEquipment> loadEquipmentList();
	
	public void saveEquipmentList(List<IEquipment> equipmentList);
}
