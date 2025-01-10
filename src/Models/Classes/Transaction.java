package Models.Classes;

import java.sql.Date;

public class Transaction {
    private int id;
    private Customer cust_id;
    private String delivery_type;
    private double delivery_Fee;
    private int expected_weight;
    private int total_cost;
    private Date created_at;
    private Date updated_at;
    private String receipt_name;
    private String receipt_address;
    private String receipt_phone;

    public Transaction(int id, Customer cust_id, String delivery_type, double delivery_Fee, int expected_weight,
            int total_cost, Date created_at, Date updated_at, String receipt_name, String receipt_address,
            String receipt_phone) {
        this.id = id;
        this.cust_id = cust_id;
        this.delivery_type = delivery_type;
        this.delivery_Fee = delivery_Fee;
        this.expected_weight = expected_weight;
        this.total_cost = total_cost;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.receipt_name = receipt_name;
        this.receipt_address = receipt_address;
        this.receipt_phone = receipt_phone;
    }

    public Transaction () {

    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Customer getCust_id() {
        return cust_id;
    }
    public void setCust_id(Customer cust_id) {
        this.cust_id = cust_id;
    }
    public String getDelivery_type() {
        return delivery_type;
    }
    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }
    public double getDelivery_Fee() {
        return delivery_Fee;
    }
    public void setDelivery_Fee(double delivery_Fee) {
        this.delivery_Fee = delivery_Fee;
    }
    public int getExpected_weight() {
        return expected_weight;
    }
    public void setExpected_weight(int expected_weight) {
        this.expected_weight = expected_weight;
    }
    public int getTotal_cost() {
        return total_cost;
    }
    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public String getReceipt_name() {
        return receipt_name;
    }
    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }
    public String getReceipt_address() {
        return receipt_address;
    }
    public void setReceipt_address(String receipt_address) {
        this.receipt_address = receipt_address;
    }
    public String getReceipt_phone() {
        return receipt_phone;
    }
    public void setReceipt_phone(String receipt_phone) {
        this.receipt_phone = receipt_phone;
    }

    
}
