package lk.ijse.pharmacy.model;



public class Orders {
    private String ordersId;
    private String date;
    private String paymentId;
    private String customerId;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Orders() {
    }

    public Orders(String ordersId, String date, String paymentId, String customerId) {
        this.ordersId = ordersId;
        this.date = date;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }
}
