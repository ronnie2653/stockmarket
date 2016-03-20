package simplestock.tradeservice.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import simplestock.dataservice.dao.api.SimpleStockDao;
import simplestock.tradeservice.api.SimpleStockService;
import simplestock.tradeservice.objectmodel.stock.CommonStock;
import simplestock.tradeservice.objectmodel.stock.PreferredStock;
import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.*;
import simplestock.tradeservice.objectmodel.trade.Trade;
import simplestock.tradeservice.objectmodel.trade.properties.BuyOrSell;
import simplestock.tradeservice.objectmodel.trade.properties.Prize;
import simplestock.tradeservice.objectmodel.trade.properties.Quantity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing functions that calculating values using List of Trades.
 * <p>
 * Created by Sandor Nagy on 3/20/16.
 */
public class SimpleStockServiceImplTradesTest {

    private List<Trade> tradesForTest;
    private SimpleStockService simpleStockService;
    private SimpleStockDao simpleStockDaoMock;

    @Before
    public void setUp() {
        tradesForTest = new LinkedList<>();   // List of Trades for test.
        simpleStockDaoMock = mock(SimpleStockDao.class);
        simpleStockService = new SimpleStockServiceImpl(simpleStockDaoMock);
        // Creating some Stocks
        Stock stockTea = new CommonStock.CommonStockBuilder(Symbol.create("TEA"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(0))
                .parValue(ParValue.create(100)).create();
        Stock stockPop = new CommonStock.CommonStockBuilder(Symbol.create("POP"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(8))
                .parValue(ParValue.create(100)).create();
        Stock stockAle = new CommonStock.CommonStockBuilder(Symbol.create("ALE"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(23))
                .parValue(ParValue.create(60)).create();
        Stock stockGin = new PreferredStock.PreferredStockBuilder(Symbol.create("GIN"), Type.create(Type.Preferred))
                .lastDividend(LastDividend.create(8)).fixedDividend(FixedDividend.create(2))
                .parValue(ParValue.create(100)).create();
        Stock stockJoe = new CommonStock.CommonStockBuilder(Symbol.create("JOE"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(13))
                .parValue(ParValue.create(200)).create();
        // Creating some Trades
        Trade tradeTea = new Trade.TradeBuilder(stockTea).buyOrSell(BuyOrSell.create("Buy")).quantity(Quantity.create(2)).prize(Prize.create(10)).create();
        Trade tradePop = new Trade.TradeBuilder(stockPop).buyOrSell(BuyOrSell.create("Sell")).quantity(Quantity.create(2)).prize(Prize.create(10)).create();
        Trade tradeAle = new Trade.TradeBuilder(stockAle).buyOrSell(BuyOrSell.create("Buy")).quantity(Quantity.create(2)).prize(Prize.create(10)).create();
        Trade tradeGin = new Trade.TradeBuilder(stockGin).buyOrSell(BuyOrSell.create("Sell")).quantity(Quantity.create(2)).prize(Prize.create(10)).create();
        Trade tradeJoe = new Trade.TradeBuilder(stockJoe).buyOrSell(BuyOrSell.create("Buy")).quantity(Quantity.create(2)).prize(Prize.create(10)).create();
        tradesForTest.add(tradeTea);
        tradesForTest.add(tradePop);
        tradesForTest.add(tradeAle);
        tradesForTest.add(tradeGin);
        tradesForTest.add(tradeJoe);
    }

    @Test
    public void testCalcVolumeWeightedStockPrizeForLastFiveMin() throws Exception {
        // any matcher because last five minute is relative.
        when(simpleStockDaoMock.queryTradeByTime(any(Date.class))).thenReturn(tradesForTest);
        BigDecimal expected = BigDecimal.valueOf(10);
        BigDecimal actual = simpleStockService.calcVolumeWeightedStockPrizeForLastFiveMin();
        assertThat(actual.toPlainString(), is(expected.toPlainString()));
    }

    @Test
    public void testCalcGBCEAllShareIndex() throws Exception {
        when(simpleStockDaoMock.queryAllTrades()).thenReturn(tradesForTest);
        BigDecimal expected = BigDecimal.valueOf(6);
        BigDecimal actual = simpleStockService.calcGBCEAllShareIndex();
        assertThat(actual, is(expected));
    }

    @After
    public void tearDown() {
        tradesForTest.clear();
    }

}
