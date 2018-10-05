package com.redhat.rhte.demo.bankservice;

public class BankRequest {

    private CreditRequest creditRequest;

    private CreditRating creditRating;

    public BankRequest() {
    }

    public BankRequest(CreditRequest creditRequest, CreditRating creditRating) {
        this.creditRequest = creditRequest;
        this.creditRating = creditRating;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }
}
