package simplestock.tradeservice.impl;

import org.junit.Before;
import org.junit.Test;
import simplestock.dataservice.dao.api.SimpleStockDao;
import simplestock.tradeservice.api.SimpleStockService;
import simplestock.tradeservice.objectmodel.stock.CommonStock.CommonStockBuilder;
import simplestock.tradeservice.objectmodel.stock.PreferredStock;
import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.*;
import simplestock.tradeservice.objectmodel.trade.properties.Prize;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Created by Sandor Nagy on 3/20/16.
 */
public class SimpleStockServiceImplTest {

    private SimpleStockService simpleStockService;
    private Prize testPrize;
    private Stock testStockCommon;
    private Stock testStockPreferred;

    @Before
    public void setUp() throws Exception {
        SimpleStockDao simpleStockDaoMock = mock(SimpleStockDao.class);
        simpleStockService = new SimpleStockServiceImpl(simpleStockDaoMock);
        testPrize = Prize.create(2);
        testStockCommon = new CommonStockBuilder(Symbol.create("POP"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(8))
                .parValue(ParValue.create(100)).create();
        testStockPreferred = new PreferredStock.PreferredStockBuilder(Symbol.create("GIN"), Type.create(Type.Preferred))
                .lastDividend(LastDividend.create(8)).fixedDividend(FixedDividend.create(2))
                .parValue(ParValue.create(100)).create();
    }

    @Test
    public void testCalcDividendYieldCommon() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(4);
        BigDecimal actual = simpleStockService.calcDividendYield(testStockCommon, testPrize);
        assertThat(actual, is(expected));
    }

    @Test
    public void testCalcDividendYieldPreferred() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(1);
        BigDecimal actual = simpleStockService.calcDividendYield(testStockPreferred, testPrize);
        assertThat(actual, is(expected));
    }

    @Test
    public void testCalcPERatio() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(0.5);
        BigDecimal actual = simpleStockService.calcPERatio(testStockCommon, testPrize).stripTrailingZeros();
        assertThat(actual, is(expected));
    }

}