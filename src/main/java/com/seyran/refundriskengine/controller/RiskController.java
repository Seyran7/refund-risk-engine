package com.seyran.refundriskengine.controller;


import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.service.RiskService;
import com.seyran.refundriskengine.service.analysis.RiskEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {
    private final RiskService riskService;

    @PostMapping("/calculate")
    public ResponseEntity<Integer> calculateRisk(@RequestBody Order order) {
        int riskScore = riskService.calculateRisk(order);
        return ResponseEntity.ok(riskScore);
    }
}
