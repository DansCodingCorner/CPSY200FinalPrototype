package Business.Interfaces;

public interface IEquipment {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getCategoryId();
    void setCategoryId(String categoryid);
    boolean isAvailable();
    void setAvailable(boolean available);
    String getDescription();
    void setDescription(String description);
    double getPrice();
    void setPrice(double price);
}
