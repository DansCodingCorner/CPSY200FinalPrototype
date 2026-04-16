package Business;

import Business.Interfaces.ICategory;


public class Category implements ICategory {

	public int id;
	
	public String name;
	
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
