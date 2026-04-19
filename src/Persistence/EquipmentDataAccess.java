package Persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Business.Equipment;
import Business.Interfaces.IEquipment;

/**Author: Daniel caron
 * Date: April 16 2026
 * Data access class to interact 
 * with the equipment.txt class.
 */
public class EquipmentDataAccess implements IEquipmentDataAccess
{
	
	//Singleton instance of equipmentDataAccess class
	static EquipmentDataAccess instance;
	
	//File path for equipment.txt file
	private final String equipmentFilePath = "src/data/equipment.txt";
	
	//Internal list of equipment objects
	private List<IEquipment> equipmentList;
	
	
	/**Private constructor for class
	 * 
	 */
	private EquipmentDataAccess()
	{
		this.equipmentList = this.loadEquipmentList();
	}

	
	/**@paramArrayList<Equipment>
	 *@return non
	 *Method to load data fromt he equipment.txt file. Called at program intialization once
	 *by equipmentManager.
	 */
	@Override
	public List<IEquipment> loadEquipmentList() 
	{
		List<IEquipment> equipmentList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(equipmentFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	Equipment equipmentToAdd = new Equipment(Integer.parseInt(lineDeconstrcuted[0]), lineDeconstrcuted[1], Integer.parseInt(lineDeconstrcuted[2]), Boolean.parseBoolean(lineDeconstrcuted[3]), lineDeconstrcuted[4], Double.parseDouble(lineDeconstrcuted[5]));
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

	
	/**@param ArrayList<Equipment>
	 * @return none
	 *Used to save the changes to the equipment.txt file made by the equipment manager class.
	 */
	@Override
	public void saveEquipmentList(List<IEquipment> equipmentList) 
	{
		equipmentList.sort(Comparator.comparingInt(IEquipment::getId));
		List<String> dataToAdd = new ArrayList<>();
		
		for(IEquipment equipment : equipmentList)
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

	/**@param none
	 * @return Singleton instance of EquipmentDataAccess object
	 * singleton constructor for the equiment data access class
	 */
	public  static EquipmentDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new EquipmentDataAccess();
		}
		
		return instance;
	}
	
	/**@param none
	 * @return ArrayList<Equipment>
	 * getter for the internal equipment list
	 */
	public List<IEquipment> getEquipmentList()
	{
		return this.equipmentList;
	}
	

}

