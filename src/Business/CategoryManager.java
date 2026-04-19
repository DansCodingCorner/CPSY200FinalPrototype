package Business;

import java.util.List;

import Business.Interfaces.ICategory;
import Business.Interfaces.ICategoryManager;
import Persistence.CategoryDataAccess;

public class CategoryManager implements  ICategoryManager
{
	private CategoryDataAccess categoryDataAccess;

	public CategoryManager() 
	{
		this.categoryDataAccess = CategoryDataAccess.getInstance();
	}

	@Override
	public ICategory searchCategoryById(int categoryId) 
	{
		List<ICategory> categories = categoryDataAccess.getCategoryList();
		
		for(ICategory category : categories)
		{
			if(category.getId() == categoryId)
			{
				return category;
			}
		}
		
		return null;
	}

	@Override
	public void addCategory(ICategory category) 
	{
		List<ICategory> categories = categoryDataAccess.loadCategories();
		
		categories.add(category);
		
		categoryDataAccess.saveCateogoryList(categories);
	}

	@Override
	public List<ICategory> getAllCategories() 
	{
		List<ICategory>categories = categoryDataAccess.loadCategories();
		
		return categories;
	}

	@Override
	public boolean updateCategory(ICategory updatedCategory) 
	{
		List<ICategory>categories = categoryDataAccess.loadCategories();

		for(ICategory category : categories)
		{
			if(updatedCategory.getId() == category.getId())
			{
				category.setName(updatedCategory.getName());
				
				categoryDataAccess.saveCateogoryList(categories);
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeCategoryById(ICategory categoryToRemove)
	{
		List<ICategory>categories = categoryDataAccess.loadCategories();

		for(ICategory category : categories)
		{
			if(categoryToRemove.getId() == category.getId())
			{
				categories.remove(category);
				
				categoryDataAccess.saveCateogoryList(categories);
				
				return true;
			}
		}
		return false;	
		}
	
	@Override
	public ICategory getCategoryById(int id) {
		List<ICategory> categories = categoryDataAccess.loadCategories();
		
		for(ICategory category : categories)
		{
			if(category.getId() == id)
			{
				System.out.println("Category ID: " + category.getId());
				System.out.println("Category Name: " + category.getName());
				return category;
			}
		}
		
		System.out.println("Category not found.");
		return null;
	}

	@Override
	public ICategory searchCategoryByName(String searchName) {
		List<ICategory> categories = categoryDataAccess.loadCategories();
		
		for(ICategory category : categories)
		{
			if(category.getName().equalsIgnoreCase(searchName))
			{
				System.out.println("Category ID: " + category.getId());
				System.out.println("Category Name: " + category.getName());
				return category;
			}
		}
		
		System.out.println("Category not found.");
		return null;
	}

}
