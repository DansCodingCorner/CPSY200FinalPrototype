package Persistence;

import java.io.*;
import java.util.*;

import Business.Category;

public class CategoryDataAccess implements ICategoryDataAccess
{
	
	static CategoryDataAccess instance;
	
	private final String categoryFilePath = "src/data/categories.txt";
	
	private ArrayList<Category> categoryList;
	
	public CategoryDataAccess()
	{
		this.categoryList = this.loadCategories();
	}

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
            e.printStackTrace();
        }
		
	}

	public  static CategoryDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new CategoryDataAccess();
		}
		
		return instance;
	}
	
	public ArrayList<Category> getCategoryList()
	{
		return this.categoryList;
	}

}
