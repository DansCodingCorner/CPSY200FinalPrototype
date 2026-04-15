package Business;

import Business.Interfaces.ICategory;

<<<<<<< HEAD
public class Category implements ICategory
{
	private int id;
	
	private String name;
=======
public class Category implements ICategory {
>>>>>>> 0f4d772583bfa25974befb1cd8e60764a978c1d9

	public Category(int id, String name) throws NumberFormatException
	{
		try
		{
			this.id = id;
			this.name = name;
		}
		catch(NumberFormatException e)
		{
			throw new NumberFormatException();
		}
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
