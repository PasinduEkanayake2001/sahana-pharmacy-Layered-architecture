package lk.ijse.pharmacy.model;

public class OrdersMedicineDetail {
    private String ordersId;
    private String medicineId;

    public OrdersMedicineDetail() {}

    public OrdersMedicineDetail(String ordersId, String medicineId) {
        this.ordersId = ordersId;
        this.medicineId = medicineId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    @Override
    public String toString() {
        return "OrdersMedicineDetail{" +
                "ordersId='" + ordersId + '\'' +
                ", medicineId='" + medicineId + '\'' +
                '}';
    }
}
