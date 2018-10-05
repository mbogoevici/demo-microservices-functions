package com.redhat.rhte.loanbroker.demoloanbrokermonolith.lending;

import com.redhat.rhte.loanbroker.demoloanbrokermonolith.CreditResponse;

import java.math.BigDecimal;

public class BankService {

    public CreditResponse calculateCredit(BankRequest bankRequest) {
        return new CreditResponse(bankRequest.getCreditRequest().getApplicantId(), CreditResponse.Status.APPROVED,
                new BigDecimal(6.0),
                bankRequest.getCreditRequest().getAmount() / 2);
    }

}

