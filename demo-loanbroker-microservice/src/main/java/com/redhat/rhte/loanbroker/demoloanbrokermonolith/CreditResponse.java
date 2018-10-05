package com.redhat.rhte.loanbroker.demoloanbrokermonolith;

import java.math.BigDecimal;

public class CreditResponse {

    public enum Status {
        APPROVED, DECLINED;
    }

    private String applicantId;

    private Status status;

    private BigDecimal rate;

    private int amount;

    public CreditResponse() {
    }

    public CreditResponse(String applicantId, Status status, BigDecimal rate, int amount) {
        this.applicantId = applicantId;
        this.status = status;
        this.rate = rate;
        this.amount = amount;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
