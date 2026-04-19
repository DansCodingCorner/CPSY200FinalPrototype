package Business;

import Business.Interfaces.IEquipment;

public final class Equipment implements IEquipment {
    private int id;
    private String name;
    private int categoryId;
    private String description;
    private boolean isAvailable;
    private double price;

    public Equipment(int id, String name, int categoryId, boolean isAvailable, String description, double price) 
    {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
        this.description = description;
        this.price = price;
    }

    public int getId() {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public String getDescription()
    {
    	return this.description;
    }
    
    public void setDescription(String newDescription)
    {
    	this.description = newDescription;
    }
    
    public double getPrice()
    {
    	return this.price;
    }
    
    public void setPrice(double newPrice)
    {
    	this.price = newPrice;
    }


}
