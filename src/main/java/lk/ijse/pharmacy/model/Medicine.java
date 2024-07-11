package lk.ijse.pharmacy.model;

public class Medicine {

    private String medicineId;
    private String name;
    private String description;
    private String qtyOnHand;
    private double price;
    private String shelfId;

    public Medicine() {
    }

    public Medicine(String medicineId, String medicineName, String medicinePrice, String medicineQty, String medicineShelfId, String shelfId){}

    public Medicine(String medicineId, String name, String description, String qtyOnHand, double price, String shelfId) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.price = price;
        this.shelfId = shelfId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", price=" + price +
                ", shelfId='" + shelfId + '\'' +
                '}';
    }
}
