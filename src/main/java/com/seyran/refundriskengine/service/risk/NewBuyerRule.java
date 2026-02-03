package com.seyran.refundriskengine.service.risk;

import com.seyran.refundriskengine.domain.model.Order;
import org.apache.tomcat.util.digester.Rule;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NewBuyerRule implements RiskRule {

    private static final int NEW_BUYER_DAYS = 7;


    @Override
    public int calculateRisk(Order order){
        if(order.getBuyer()==null||order.getBuyer().getCreatedAt()==null){
            return 0;
        }
        long accountAgeDays = ChronoUnit.DAYS.between(order.getBuyer().getCreatedAt(), LocalDate.now());
        if(accountAgeDays<NEW_BUYER_DAYS){
            return 15;
        }
        return 0;
    }
}
