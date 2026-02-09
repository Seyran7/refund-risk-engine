package com.seyran.refundriskengine.service;

import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.risk.RiskRule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class NewBuyerRule implements RiskRule {

    private static final int NEW_BUYER_DAYS = 7;


    @Override
    public int calculateRisk(Order order){
        if(order.getBuyer()==null||order.getBuyer().getCreatedAt()==null){
            return 0;
        }
        long accountAgeDays = ChronoUnit.DAYS.between(
                order.getBuyer().getCreatedAt().toLocalDate(),
                LocalDateTime.now().toLocalDate());
        if(accountAgeDays < NEW_BUYER_DAYS) {
            return 15;
        }
        return 0;

    }
    @Override
    public int priority() {
        return 0;
    }
}
