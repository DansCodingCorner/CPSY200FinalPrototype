package Persistence;

import java.util.ArrayList;

import Business.Category;

public interface ICategoryDataAccess 
{

	ArrayList<Category> loadCategories();
	
	void saveCateogoryList(ArrayList<Category> categoryList);
	
}
