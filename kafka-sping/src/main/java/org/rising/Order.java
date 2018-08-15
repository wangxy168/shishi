package org.rising;

import java.util.Date;
import java.util.List;

public class Order {

    private String orderId;
    private Integer userId;
    private Long price;
    private List<Long> itemIds;
    private Date created;
    private Date updated;

    public Order() {
    }

    public Order(String orderId, Integer userId, Long price, List<Long> itemIds, Date created, Date updated) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.itemIds = itemIds;
        this.created = created;
        this.updated = updated;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", price=" + price +
                ", itemIds=" + itemIds +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
