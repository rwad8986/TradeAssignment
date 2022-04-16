package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;


public class PriceListenerImpl implements PriceListener {

    private TradingStrategy tradingStrategy;
    private final ExecutionService executionService;

    public PriceListenerImpl(TradingStrategy tradingStrategy, ExecutionService executionService) {
        this.tradingStrategy = tradingStrategy;
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        tradingStrategy.performTrade(security, price);
    }
}
