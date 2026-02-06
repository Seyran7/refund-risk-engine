package com.seyran.refundriskengine.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RiskLevelTest {
    @Test
    void testLowRisk(){
        assertEquals(RiskLevel.LOW, RiskLevel.fromScore(10));
        assertEquals(RiskLevel.LOW, RiskLevel.fromScore(0));
        assertEquals(RiskLevel.LOW, RiskLevel.fromScore(29));
    }
    @Test
    void testMediumRisk(){
        assertEquals(RiskLevel.MEDIUM, RiskLevel.fromScore(30));
        assertEquals(RiskLevel.MEDIUM, RiskLevel.fromScore(45));
        assertEquals(RiskLevel.MEDIUM, RiskLevel.fromScore(59));
    }
    @Test
    void testHighRisk(){
        assertEquals(RiskLevel.HIGH, RiskLevel.fromScore(60));
        assertEquals(RiskLevel.HIGH, RiskLevel.fromScore(80));
        assertEquals(RiskLevel.HIGH, RiskLevel.fromScore(100));
    }
    @Test
    void testInvalidScore(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            RiskLevel.fromScore(-1);
        });
        assertTrue(exception.getMessage().contains("Invalid score value"));

        exception = assertThrows(IllegalArgumentException.class,()->{
            RiskLevel.fromScore(101);
        });
        assertTrue(exception.getMessage().contains("Invalid score value"));
    }
}
