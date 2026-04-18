package Persistence;

import java.util.List;
import Business.Interfaces.ICategory;

public interface ICategoryDataAccess 
{

	List<ICategory> loadCategories();
	
	void saveCateogoryList(List<ICategory> categoryList);
	
}
