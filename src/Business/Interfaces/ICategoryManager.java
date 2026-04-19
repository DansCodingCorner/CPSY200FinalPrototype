package Business.Interfaces;

import java.util.List;


public interface ICategoryManager 
{

	public ICategory searchCategoryById(int categoryId);

	public void addCategory(ICategory category);

	public List<ICategory> getAllCategories();

	public boolean updateCategory(ICategory updatedCategory);

	public boolean removeCategoryById(ICategory categoryToRemove);

	void getCategoryById(int id);

}
