package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.replay;

public class TradingStrategyTest {

    private TradingStrategy tradingStrategy;

    @Mock
    ExecutionService executionService;

    @Before
    public void setup() {
        executionService = EasyMock.createNiceMock(ExecutionService.class);
        Map<String, Double> buyTriggerForSecurities = new HashMap<>();
        buyTriggerForSecurities.put("IBM", 55d);
        tradingStrategy = new TradingStrategy(buyTriggerForSecurities, executionService, 100);

    }

    @Test
    public void testPerformTradeForSell() {
        executionService.buy("IBM", 45d, 100);
        EasyMock.expectLastCall().times(1);
        replay(executionService);
        tradingStrategy.performTrade("IBM", 45d);
        EasyMock.verify(executionService);
    }

    @Test
    public void testPerformTradeForNoSell() {
        executionService.buy("IBM", 65d, 100);
        //verify buy is not called.
        EasyMock.expectLastCall().andStubThrow(new AssertionError());
        replay(executionService);
        tradingStrategy.performTrade("IBM", 65d);
        EasyMock.verify(executionService);
    }


}
