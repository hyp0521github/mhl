package com.yz.mhl.domain;

/**
 * @author 院长
 * @version 1.0.0
 */
public class Dining {
    private Integer id;
    private String status;
    private String orderName;
    private String orderTel;

    public Dining() {
    }

    public Dining(Integer id, String status, String orderName, String orderTel) {
        this.id = id;
        this.status = status;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + status;
    }
}
