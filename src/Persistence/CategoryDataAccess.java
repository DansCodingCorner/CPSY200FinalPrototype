package Persistence;

import java.io.*;
import java.util.*;

import Business.Category;

public class CategoryDataAccess implements ICategoryDataAccess
{
	/**
	 * Author Daniel Caron
	 * Last edit: April 16
	 * THis class is used to interact with
	 * the Category.txt file.
	 * It is able to Load the file into an array of categroy objects
	 * and to save a given list of category objects into
	 * the category.txt file.
	 * It is a singleton class, generated using the getInstance method.
	 */
	
	//Instance as it is a singleton 
	static CategoryDataAccess instance;
	
	//File path to the categories.txt file
	private final String categoryFilePath = "src/data/categories.txt";
	
	//Internal category list for the getter method
	private ArrayList<Category> categoryList;
	
	/**
	 * Constructor for CategoryDataAcess
	 * When construced, will load the list of 
	 * available categories
	 */
	private CategoryDataAccess()
	{
		
		this.categoryList = this.loadCategories();
	}

	/**
	 *Method: loadCategories
	 *params: none
	 *Returns: An ArrayList of Category objects
	 *Used to load the category.txt file into an array for use
	 *by the CategoryManager Class
	 */
	@Override
	public ArrayList<Category> loadCategories() 
	{
		
		ArrayList<Category> categoryList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(categoryFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	Category categoryToAdd = new Category (Integer.parseInt(lineDeconstrcuted[0]), lineDeconstrcuted[1]);
            	categoryList.add(categoryToAdd);
            }
            //Testing pull requests GH
            return categoryList;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("categories.txt file is empty");
		return categoryList;
	}

	
	/**
	 *Method: saveCategoryList
	 *params: An arrayList of Category objects
	 *Returns: Void
	 *Used to save the changes made in the CategoryManager class
	 *to the categories.txt file.
	 *Called when initializing the program.
	 */
	@Override
	public void saveCateogoryList(ArrayList<Category> categoryList) 
	{
		ArrayList<String> dataToAdd = new ArrayList<>();
		
		for(Category category : categoryList)
		{
			String data = Integer.toString(category.getId()) + "," + category.getName();
			dataToAdd.add(data);
		}
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(categoryFilePath))) 
        {
		for(String data : dataToAdd)
		{
            writer.write(data);
            writer.newLine();
        } 
        }
		catch (IOException e) 
		{
			System.out.println("Error encounteded, error when accessing the categories.txt file. Ensure integrity of data folder in system.");
            e.printStackTrace();
        }
		
	}

	
	/**
	 *Method: getInstance
	 *params: None
	 *Returns: CategoryDataAccess Object
	 *The singleton constructor for the Category data 
	 *access class. Ensures only a single data access
	 *object exists for the category file, preventing
	 *errros in data manipulation.
	 */
	public  static CategoryDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new CategoryDataAccess();
		}
		
		return instance;
	}
	
	/**
	 *Method: getCategoryList
	 *params: none
	 *Returns: ArrayList<Category>
	 *Returns the categoryList stored within the categoryDataAcess
	 *instance
	 */
	public ArrayList<Category> getCategoryList()
	{
		return this.categoryList;
	}

}
