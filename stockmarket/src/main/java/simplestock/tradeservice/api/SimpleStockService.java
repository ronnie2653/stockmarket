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
     * Returns the Dividend Yield of the input stock for the input prize.
     * <p>
     * Equation:
     * Common Stock: Last Dividend / Price
     * Preferred Stock: Fixed Dividend * ParValue / Price
     */
    BigDecimal calcDividendYield(Stock stock, Prize prize);

    /**
     * Returns the P/E ration of the input stock for the input prize.
     * <p>
     * Equation:
     * P/E Ratio = Price/Dividend
     */
    BigDecimal calcPERatio(Stock stock, Prize prize);

    /**
     * Records a trade.
     * Timestamp value is the system current time when the trade is recorded.
     */
    void recordTrade(Trade trade);

    /**
     * Returns the Volume Weighted Stock Prize (VWSP) for trades from the last five minutes.
     * <p>
     * Equation:
     * VWSP for last five min =  sum(ti.prize + ti.quantity) / sum(ti.quantity) where ti is the ith trade in five 5 min
     * and 0 <= i <= n : index of trades in five min.
     */
    BigDecimal calcVolumeWeightedStockPrizeForLastFiveMin();

    /**
     * Returns the GBCE All Share Index for all trade.
     * Which is the Geometric Mean of the Volume Weighted Stock Price for all stocks.
     * <p>
     * Equation:
     * GBCE All Share Index = nth root of p1*p2...pn where pi = Volume Weighted Stock Price for the ith trade
     */
    BigDecimal calcGBCEAllShareIndex();

}
