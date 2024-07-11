package lk.ijse.pharmacy.model;

public class Shelf {
    private String shelfId;
    private int capacity;

    public Shelf(){}

    public Shelf(String shelfId, int capacity) {
        this.shelfId = shelfId;
        this.capacity = capacity;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfId='" + shelfId + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
