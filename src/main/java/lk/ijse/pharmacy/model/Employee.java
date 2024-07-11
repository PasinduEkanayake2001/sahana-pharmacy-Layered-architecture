package lk.ijse.pharmacy.model;

public class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private String address;
    private String tel;
    private String userId;

    public Employee(){}

    public Employee(String employeeId, String name, double salary, String address, String tel, String userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.tel = tel;
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
