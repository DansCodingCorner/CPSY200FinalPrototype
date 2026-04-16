package Persistence;

import java.util.ArrayList;

import Business.Equipment;

public interface IEquipmentDataAccess 
{
	public ArrayList<Equipment> saveEquipmentList();
	
	public void saveEquipmentList(ArrayList<Equipment> equipmentList);
}
