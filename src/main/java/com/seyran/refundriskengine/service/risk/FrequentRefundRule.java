package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class FrequentRefundRule implements RiskRule {
    @Override
    public int calculateRisk(Order order){
        if (order.getBuyer().getTotalRefunds()>=3){
            return 30;
        }
        return 0;
    }
}
