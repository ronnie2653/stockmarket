package simplestock.tradeservice.api;

import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.trade.Trade;
import simplestock.tradeservice.objectmodel.trade.properties.Prize;

import java.math.BigDecimal;

/**
 * Simple Stock Service API that defines the required method for Simple Stock Market.
 * <p>
 * Created by Sandor Nagy on 3/20/16.
 */
public interface SimpleStockService {

    /**
     * <pre>
     * Returns the Dividend Yield of the input stock for the input prize.
     * Equation:
     * Common Stock: Last Dividend / Price
     * Preferred Stock: Fixed Dividend * ParValue / Price
     * </pre>
     */
    BigDecimal calcDividendYield(Stock stock, Prize prize);

    /**
     * <pre>
     * Returns the P/E ration of the input stock for the input prize.
     * Equation:
     * P/E Ratio = Price/Dividend
     * <pre>
     */
    BigDecimal calcPERatio(Stock stock, Prize prize);

    /**
     * <pre>
     * Records a trade.
     * Timestamp value is the system current time when the trade is recorded.
     * </pre>
     */
    void recordTrade(Trade trade);

    /**
     * <pre>
     * Returns the Volume Weighted Stock Prize (VWSP) for trades from the last five minutes.
     * Equation:
     * VWSP for last five min =  sum(ti.prize + ti.quantity) / sum(ti.quantity) where ti is the ith trade in five 5 min
     * and 0 <= i <= n : index of trades in five min.
     * </pre>
     */
    BigDecimal calcVolumeWeightedStockPrizeForLastFiveMin();

    /**
     * <pre>
     * Returns the GBCE All Share Index for all trade.
     * Which is the Geometric Mean of the Volume Weighted Stock Price for all stocks.
     * Equation:
     * GBCE All Share Index = nth root of p1*p2...pn where pi = Volume Weighted Stock Price for the ith trade
     * </pre>
     */
    BigDecimal calcGBCEAllShareIndex();

}
