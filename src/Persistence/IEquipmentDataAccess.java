package Persistence;

import java.util.ArrayList;

import Business.Equipment;

public interface IEquipmentDataAccess 
{
	public ArrayList<Equipment> loadEquipmentList();
	
	public void saveEquipmentList(ArrayList<Equipment> equipmentList);
}
