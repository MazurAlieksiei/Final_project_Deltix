package org.deltix.models;

import java.util.Objects;

public class Item {
    Integer all;
    String orderType;
    Integer negative;
    Integer positive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(all, item.all) && Objects.equals(orderType, item.orderType) && Objects.equals(negative, item.negative) && Objects.equals(positive, item.positive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(all, orderType, negative, positive);
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }
}
