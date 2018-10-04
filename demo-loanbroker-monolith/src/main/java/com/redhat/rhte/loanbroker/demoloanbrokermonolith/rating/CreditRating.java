package com.redhat.rhte.loanbroker.demoloanbrokermonolith.rating;

public class CreditRating {

    private String applicantId;

    private int score;

    public CreditRating() {
    }

    public CreditRating(String applicantId, int score) {
        this.applicantId = applicantId;
        this.score = score;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
