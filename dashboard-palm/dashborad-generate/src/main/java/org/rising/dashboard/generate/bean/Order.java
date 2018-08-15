package org.rising.dashboard.generate.bean;

import java.util.Date;

public class Order {
	private String orderId;//id,rowKye:id+时间戳
	private Double payment;//实付金额
	private Integer paymentType; //支付类型，1、在线支付，2、货到付款
	private String postFee;//邮费
	/**
	 * 初始阶段：1、未付款、未发货；初始化所有数据
	 * 付款阶段：2、已付款；更改付款时间
	 * 发货阶段：3、已发货；更改发货时间、更新时间、物流名称、物流单号
	 * 成功阶段：4、已成功；更改更新时间，交易结束时间，买家留言，是否已评价
	 * 关闭阶段：5、关闭；   更改更新时间，交易关闭时间。
	 * */
	private Integer status;//状态:1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private Date paymentTime;//付款时间
	private Date consignTime;//发货时间
	private Date endTime;//交易结束时间
	private Date closeTime;//交易关闭时间
	private String shippingName;//物流名称
	private String shippingCode;//物流单号
	private Long userId;//用户id

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", payment='" + payment + '\'' +
                ", paymentType=" + paymentType +
                ", postFee='" + postFee + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", paymentTime=" + paymentTime +
                ", consignTime=" + consignTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", shippingName='" + shippingName + '\'' +
                ", shippingCode='" + shippingCode + '\'' +
                ", userId=" + userId +
                '}';
    }
}