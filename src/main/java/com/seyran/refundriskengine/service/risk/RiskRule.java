package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
;


public interface RiskRule {
    int calculateRisk(Order order);
    default int priority() {
        return 0; // default priority
    }
}
