package simplestock.dataservice.dao.api;

import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.Symbol;
import simplestock.tradeservice.objectmodel.trade.Trade;

import java.util.Date;
import java.util.List;

/**
 * API for managing the Collection of Stocks and Trades.
 * <p>
 * Created by Sandor Nagy on 3/13/16.
 */
public interface SimpleStockDao {

    /**
     * Returns every Stock with the input symbol.
     */
    List<Stock> queryStockBySymbol(Symbol symbol);

    /**
     * Inserts the input stock.
     */
    void insertStock(Stock stock);

    /**
     * Returns every Trade that happened at or after the input time.
     *
     * @param date the lower time bound for the query
     */
    List<Trade> queryTradeByTime(Date date);

    /**
     * Inserts input trade.
     */
    void insertTrade(Trade trade);

    /**
     * Returns all the trades in the database.
     */
    List<Trade> queryAllTrades();

}
