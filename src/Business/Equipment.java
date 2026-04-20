package Business;

public final class Equipment {
    private int id;
    private String name;
    private String categoryid;
    private String description;
    private boolean isAvailable;
    private double price;
	private Object equipmentid;

    public Equipment(int id, String name, String categoryid, boolean isAvailable, String description, double price, Object equipmentid) 
    {
        this.id = id;
        this.name = name;
        this.categoryid = categoryid;
        this.isAvailable = isAvailable;
        this.description = description;
        this.price = price;
        this.equipmentid = equipmentid;
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
        return categoryid;
    }

    public void setCategoryId(String categoryid) {
        this.categoryid = categoryid;
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

	public int getEquipmentId() {
		return this.id;
}

	public void setEquipmentId(int i, Object equipmentid) {
		
		this.equipmentid = equipmentid;
		
	}
}	
