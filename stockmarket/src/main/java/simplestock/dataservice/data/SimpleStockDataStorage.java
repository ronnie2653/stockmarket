package simplestock.dataservice.data;

import simplestock.tradeservice.objectmodel.stock.CommonStock.CommonStockBuilder;
import simplestock.tradeservice.objectmodel.stock.PreferredStock.PreferredStockBuilder;
import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.*;
import simplestock.tradeservice.objectmodel.trade.Trade;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class contains the recorded trades and stocks. It's like a mock DataBase.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public final class SimpleStockDataStorage {

    // This static variable holds the stored Stocks. It's like a mock table.
    private static final List<Stock> GlobalBeverageCorporationStocks = new LinkedList<>();
    // This static variable holds the stored Trades.
    private static final List<Trade> GlobalBeverageCorporationTrades = new LinkedList<>();

    static {
        // Static initializer block to add the Sample Data values in to the collection of Stocks
        Stock stock_1 = new CommonStockBuilder(Symbol.create("TEA"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(0))
                .parValue(ParValue.create(100)).create();
        Stock stock_2 = new CommonStockBuilder(Symbol.create("POP"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(8))
                .parValue(ParValue.create(100)).create();
        Stock stock_3 = new CommonStockBuilder(Symbol.create("ALE"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(23))
                .parValue(ParValue.create(60)).create();
        Stock stock_4 = new PreferredStockBuilder(Symbol.create("GIN"), Type.create(Type.Preferred))
                .lastDividend(LastDividend.create(8)).fixedDividend(FixedDividend.create(2))
                .parValue(ParValue.create(100)).create();
        Stock stock_5 = new CommonStockBuilder(Symbol.create("JOE"), Type.create(Type.Common))
                .lastDividend(LastDividend.create(13))
                .parValue(ParValue.create(200)).create();

        GlobalBeverageCorporationStocks.add(stock_1);
        GlobalBeverageCorporationStocks.add(stock_2);
        GlobalBeverageCorporationStocks.add(stock_3);
        GlobalBeverageCorporationStocks.add(stock_4);
        GlobalBeverageCorporationStocks.add(stock_5);
    }


    // *** Synchronized methods for managing the collection of Stocks and Trades. ***

    /**
     * Thread safe method to add a Stock to the collection of Stocks.
     */
    synchronized public static void addStock(Stock stock) {
        GlobalBeverageCorporationStocks.add(stock);
    }

    /**
     * Thread safe method to return a List of Stocks identified by they Symbol.
     *
     * @param symbol input to identify which stocks to select
     * @return list of stocks which contains the input symbol
     */
    synchronized public static List<Stock> getStocksBySymbol(Symbol symbol) {
        return GlobalBeverageCorporationStocks.stream().filter(stock -> stock.getSymbol().getValue().equals(symbol.getValue())).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Thread safe method to add Trade to the collection of Trades.
     *
     * @param trade input to store
     */
    synchronized public static void addTrade(Trade trade) {
        GlobalBeverageCorporationTrades.add(trade);
    }

    /**
     * Thread safe method that returns every trade that happened at or after the input time.
     *
     * @param date lower bound for the query
     * @return list of trades that happened at or after the input time
     */
    synchronized public static List<Trade> getTradesByTime(Date date) {
        return GlobalBeverageCorporationTrades.stream().filter(trade -> trade.getTime().getValue() >= date.getTime()).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * This method returns a copy of all the trades in the GlobalBeverageCorporationTrades.
     */
    synchronized public static List<Trade> getAllTrades() {
        return GlobalBeverageCorporationTrades.stream().map(Trade::new).collect(Collectors.toCollection(LinkedList::new));
    }

}
