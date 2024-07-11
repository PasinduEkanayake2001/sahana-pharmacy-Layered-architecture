package lk.ijse.pharmacy.model;

public class MedicineSupplierDetail {
    private String medicineId;
    private String supplierId;

    public MedicineSupplierDetail(){}

    public MedicineSupplierDetail(String medicineId, String supplierId) {
        this.medicineId = medicineId;
        this.supplierId = supplierId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "MedicineSupplierDetail{" +
                "medicineId='" + medicineId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }
}
