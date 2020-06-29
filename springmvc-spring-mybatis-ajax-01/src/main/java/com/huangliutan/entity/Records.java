package com.huangliutan.entity;

public class Records {
    private String id;
    private String periodNumber;
    private String repaymentDate;
    private String monthlyPrincipal;
    private String monthlyInterest;
    private String monthlyRent;
    private String totalA;

    @Override
    public String toString() {
        return "Records{" +
                "id='" + id + '\'' +
                ", periodNumber='" + periodNumber + '\'' +
                ", repaymentDate='" + repaymentDate + '\'' +
                ", monthlyPrincipal='" + monthlyPrincipal + '\'' +
                ", monthlyInterest='" + monthlyInterest + '\'' +
                ", monthlyRent='" + monthlyRent + '\'' +
                ", totalA='" + totalA + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getMonthlyPrincipal() {
        return monthlyPrincipal;
    }

    public void setMonthlyPrincipal(String monthlyPrincipal) {
        this.monthlyPrincipal = monthlyPrincipal;
    }

    public String getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(String monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public String getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(String monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getTotalA() {
        return totalA;
    }

    public void setTotalA(String totalA) {
        this.totalA = totalA;
    }
}
