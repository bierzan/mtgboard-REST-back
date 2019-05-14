package com.brzn.mtgboard.card.wanted;

public enum Condition {
    M("Mint"),
    NM("Near Mint"),
    EX("Excellent"),
    GD("Good"),
    LP("Light Played"),
    PL("Played"),
    POOR("Poor");

    private String fullName;

    Condition(String condition) {
        this.fullName = condition;
    }

    public String getFullName() {
        return fullName;
    }
}
