package lk.ijse.pharmacy.model;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String tel;
    private String userId;

    public Customer(){
    }

    public Customer(String customerId, String name, String address, String tel, String userId) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
