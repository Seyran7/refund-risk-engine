package com.seyran.refundriskengine.domain.model;

public enum RiskLevel {
    LOW,
    MEDIUM,
    HIGH;

    public static RiskLevel fromScore(int score){
        if(score<0||score>100){
           throw new IllegalArgumentException("Invalid  risk score: " + score);
        }else if(score>=60){
            return HIGH;
        }else if(score>=30){
            return MEDIUM;
        }else {
            return LOW;
        }

    }
}
