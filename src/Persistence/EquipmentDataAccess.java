package Persistence;

import java.io.*;
import java.util.ArrayList;

import Business.Equipment;

public class EquipmentDataAccess implements IEquipmentDataAccess
{
	
	static EquipmentDataAccess instance;
	
	private final String equipmentFilePath = "src/data/equipment.txt";
	
	private ArrayList<Equipment> equipmentList;
	
	public EquipmentDataAccess()
	{
		this.equipmentList = this.loadEquipmentList();
	}

	@Override
	public ArrayList<Equipment> loadEquipmentList() 
	{
		ArrayList<Equipment> equipmentList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(equipmentFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	Equipment equipmentToAdd = new Equipment (Integer.parseInt(lineDeconstrcuted[0]), lineDeconstrcuted[1],lineDeconstrcuted[2],Boolean.parseBoolean(lineDeconstrcuted[3]),lineDeconstrcuted[4],Double.parseDouble(lineDeconstrcuted[5]));
            	equipmentList.add(equipmentToAdd);
            }
            return equipmentList;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("equipment.txt file is empty");
		return equipmentList;
	}

	@Override
	public void saveEquipmentList(ArrayList<Equipment> equipmentList) 
	{
		ArrayList<String> dataToAdd = new ArrayList<>();
		
		for(Equipment equipment : equipmentList)
		{
			String data = Integer.toString(equipment.getId()) + "," + equipment.getName() + "," + equipment.getCategoryId() + "," + equipment.isAvailable() + "," + equipment.getDescription() + "," + equipment.getPrice();
			dataToAdd.add(data);
		}
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(equipmentFilePath))) 
        {
		for(String data : dataToAdd)
		{
            writer.write(data);
            writer.newLine();
        } 
        }
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
	}

	public  static EquipmentDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new EquipmentDataAccess();
		}
		
		return instance;
	}
	
	public ArrayList<Equipment> getEquipmentList()
	{
		return this.equipmentList;
	}
	
	public static void main(String[] args)
	{
		EquipmentDataAccess ed = EquipmentDataAccess.getInstance();
		System.out.println(ed.loadEquipmentList());
		ed.saveEquipmentList(ed.getEquipmentList());
	}
}

