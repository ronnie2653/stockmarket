package simplestock.tradeservice.impl;

import simplestock.dataservice.dao.api.SimpleStockDao;
import simplestock.tradeservice.api.SimpleStockService;
import simplestock.tradeservice.objectmodel.stock.PreferredStock;
import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.Type;
import simplestock.tradeservice.objectmodel.trade.Trade;
import simplestock.tradeservice.objectmodel.trade.properties.Prize;
import simplestock.utils.Calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * SimpleStockService implementation.
 * <p>
 * Created by Sandor Nagy on 3/20/16.
 */
public class SimpleStockServiceImpl implements SimpleStockService {

    private SimpleStockDao simpleStockDao;

    /**
     * Constructor for injecting simpleStockDao.
     */
    public SimpleStockServiceImpl(SimpleStockDao simpleStockDao) {
        this.simpleStockDao = simpleStockDao;
    }

    @Override
    public BigDecimal calcDividendYield(Stock stock, Prize prize) {
        BigDecimal dividendYield = null;
        if (Type.Common.equals(stock.getType().getValue())) {
            dividendYield = BigDecimal.valueOf(stock.getLastDividend().getValue())
                    .divide(BigDecimal.valueOf(prize.getValue()), 10, ROUND_HALF_UP);
        } else if (Type.Preferred.equals(stock.getType().getValue())) {
            dividendYield = BigDecimal.valueOf(((PreferredStock) stock).getFixedDividend().getValue())
                    .multiply(BigDecimal.valueOf(stock.getParValue().getValue()))
                    .divide(BigDecimal.valueOf(prize.getValue()), 10, ROUND_HALF_UP);
        }

        if (dividendYield == null) {
            throw new UnsupportedOperationException("Error: Unsupported operation for Stock");
        } else {
            return dividendYield.stripTrailingZeros();
        }
    }

    @Override
    public BigDecimal calcPERatio(Stock stock, Prize prize) {
        return BigDecimal.valueOf(prize.getValue()).divide(calcDividendYield(stock, prize), 10, ROUND_HALF_UP).stripTrailingZeros();
    }

    @Override
    public void recordTrade(Trade trade) {
        simpleStockDao.insertTrade(trade);
    }

    @Override
    public BigDecimal calcVolumeWeightedStockPrizeForLastFiveMin() {
        int fiveMinInMilliSec = 5 * 60 * 1000;
        Date fiveMinAgo = new Date(System.currentTimeMillis() - fiveMinInMilliSec);
        List<Trade> lastFiveMinTrades = simpleStockDao.queryTradeByTime(fiveMinAgo);
        int sumOfQuantityAndPrize = 0;
        int sumOfQuantity = 0;
        for (Trade trade : lastFiveMinTrades) {
            sumOfQuantityAndPrize += trade.getPrize().getValue() * trade.getQuantity().getValue();
            sumOfQuantity += trade.getQuantity().getValue();
        }
        return BigDecimal.valueOf(sumOfQuantityAndPrize).divide(BigDecimal.valueOf(sumOfQuantity), ROUND_HALF_UP).setScale(10, ROUND_HALF_UP).stripTrailingZeros();
    }

    @Override
    public BigDecimal calcGBCEAllShareIndex() {
        // Volume Weighted Stock Prize for every Trade.
        List<BigDecimal> volumeWeightedPrizes = simpleStockDao.queryAllTrades().stream().map(this::calcVolumeWeightedStockPrizeForTrade).collect(Collectors.toCollection(LinkedList::new));
        return calcNthRoot(volumeWeightedPrizes).setScale(10, ROUND_HALF_UP).stripTrailingZeros();
    }

    // Returns the Volume Weighted Stock Price for a Trade.
    private BigDecimal calcVolumeWeightedStockPrizeForTrade(Trade trade) {
        return BigDecimal.valueOf(trade.getPrize().getValue() + trade.getQuantity().getValue()).divide(BigDecimal.valueOf(trade.getQuantity().getValue()), 10, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    // Returns the nth root of the input list of BigDecimals.
    private BigDecimal calcNthRoot(List<BigDecimal> inputNumbers) {
        int num = inputNumbers.size();
        BigDecimal multiplicationResult = BigDecimal.ONE;
        for (BigDecimal inNumber : inputNumbers) {
            multiplicationResult = multiplicationResult.multiply(inNumber);
        }
        return Calculation.nthRoot(multiplicationResult, num).setScale(10, ROUND_HALF_UP);
    }

}
