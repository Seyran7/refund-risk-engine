package com.seyran.refundriskengine.controller;


import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {
    private final RiskService riskService;

    @PostMapping("/calculate")
    public ResponseEntity<Map<String,Object>> calculateRisk(@RequestBody Order order) {
        int riskScore = riskService.calculateRisk(order);
        Map<String,Object> response = new HashMap<>();
        response.put("riskScore", riskService);
        response.put("OrderId", order.getId());
        response.put("riskLevel",resolveRiskLevel(riskScore));
        return ResponseEntity.ok(response);
    }
    private String resolveRiskLevel(int riskScore){
        if(riskScore >=70) return "HIGh";
        if(riskScore >=40) return "Medium";
        return "Low";
    }
}
