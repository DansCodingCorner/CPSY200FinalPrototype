package Business;

public final class Equipment {
    private int id;
    private String name;
    private String categoryid;
    private boolean isAvailable;

    public Equipment(int id, String name, String categoryid, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.categoryid = categoryid;
        this.isAvailable = isAvailable;
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
}
