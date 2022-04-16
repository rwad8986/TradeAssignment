package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;

import java.util.Map;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private final ExecutionService executionService;
    private final Map<String, Double> buyTriggerForSecurities;
    private final int volume;

    public TradingStrategy(Map<String, Double> buyTriggerForSecurities, ExecutionService executionService, int volume) {
        this.buyTriggerForSecurities = buyTriggerForSecurities;
        this.executionService = executionService;
        this.volume = volume;
    }

    public void performTrade(String security, double price) {
        if (price < buyTriggerForSecurities.get(security)) {
            executionService.buy(security, price, volume);
        }
    }
}
