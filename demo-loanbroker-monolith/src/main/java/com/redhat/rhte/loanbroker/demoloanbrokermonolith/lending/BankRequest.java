package com.redhat.rhte.loanbroker.demoloanbrokermonolith.lending;

import com.redhat.rhte.loanbroker.demoloanbrokermonolith.CreditRequest;
import com.redhat.rhte.loanbroker.demoloanbrokermonolith.rating.CreditRating;

public class BankRequest {

    private CreditRequest creditRequest;

    private CreditRating creditRating;

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
