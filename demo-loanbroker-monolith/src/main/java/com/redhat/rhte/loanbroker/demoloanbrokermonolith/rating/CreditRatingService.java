package com.redhat.rhte.loanbroker.demoloanbrokermonolith.rating;

public class CreditRatingService {

    public CreditRating rate(String applicantId) {
        // flesh this out
        if (applicantId.startsWith("A"))
            return new CreditRating(applicantId, 900);
        if (applicantId.startsWith("B"))
            return new CreditRating(applicantId, 800);
        if (applicantId.startsWith("C"))
            return new CreditRating(applicantId, 700);
        if (applicantId.startsWith("D"))
            return new CreditRating(applicantId, 600);
        if (applicantId.startsWith("E"))
            return new CreditRating(applicantId, 500);
        if (applicantId.startsWith("F"))
            return new CreditRating(applicantId, 400);
        return new CreditRating(applicantId, 300);
    }
}
