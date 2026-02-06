package com.seyran.refundriskengine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class RiskResult {
    private int score;
    private RiskLevel level;

}
