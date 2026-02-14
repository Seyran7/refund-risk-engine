package com.seyran.refundriskengine.controller;


import com.seyran.refundriskengine.domain.model.Order;
import com.seyran.refundriskengine.domain.model.RiskResult;
import com.seyran.refundriskengine.dto.OrderRequestDto;
import com.seyran.refundriskengine.mapper.OrderMapper;
import com.seyran.refundriskengine.service.risk.RiskService;
import jakarta.validation.Valid;
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
    private final OrderMapper orderMapper;

    @PostMapping("/calculate")
    public ResponseEntity<RiskResult> calculate(@Valid@RequestBody OrderRequestDto request) {
        Order order= orderMapper.toOrder(request);
        RiskResult riskResult=riskService.calculateRisk(order);
        return ResponseEntity.ok(riskResult);
    }
    private String resolveRiskLevel(int riskScore){
        if(riskScore >=70) return "HIGh";
        if(riskScore >=40) return "Medium";
        return "Low";
    }
}
