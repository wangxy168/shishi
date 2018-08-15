package org.rising.dashboard.generate.bean;

import java.util.Date;

public class User {
    private Long id;
    private Integer sex;
    private Integer vipLevel;
    private String birthYear;
    private String province;
    private String city;
    private String channel;
    private Date firstPay;
    private Date firstVisit;
    private Date latestPay;
    private Date latestVisit;
    private Date signupTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(Date firstPay) {
        this.firstPay = firstPay;
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Date getLatestPay() {
        return latestPay;
    }

    public void setLatestPay(Date latestPay) {
        this.latestPay = latestPay;
    }

    public Date getLatestVisit() {
        return latestVisit;
    }

    public void setLatestVisit(Date latestVisit) {
        this.latestVisit = latestVisit;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", vipLevel=" + vipLevel +
                ", birthYear='" + birthYear + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", channel='" + channel + '\'' +
                ", firstPay=" + firstPay +
                ", firstVisit=" + firstVisit +
                ", latestPay=" + latestPay +
                ", latestVisit=" + latestVisit +
                ", signupTime=" + signupTime +
                '}';
    }
}
