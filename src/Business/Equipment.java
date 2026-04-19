package Business;

import Business.Interfaces.IEquipment;

public final class Equipment implements IEquipment {
    private int id;
    private String name;
    private String categoryId;
    private String description;
    private boolean isAvailable;
    private double price;

    public Equipment(int id, String name, String categoryId, boolean isAvailable, String description, double price) 
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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
