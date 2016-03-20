package simplestock.dataservice.dao.impl;

import simplestock.dataservice.dao.api.SimpleStockDao;
import simplestock.dataservice.data.SimpleStockDataStorage;
import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.stock.properties.Symbol;
import simplestock.tradeservice.objectmodel.trade.Trade;

import java.util.Date;
import java.util.List;

/**
 * Created by Sandor Nagy on 3/13/16.
 */
public class SimpleStockDaoImpl implements SimpleStockDao {

    @Override
    synchronized public List<Stock> queryStockBySymbol(Symbol symbol) {
        return SimpleStockDataStorage.getStocksBySymbol(symbol);
    }

    @Override
    public void insertStock(Stock stock) {
        SimpleStockDataStorage.addStock(stock);
    }

    @Override
    public List<Trade> queryTradeByTime(Date date) {
        return SimpleStockDataStorage.getTradesByTime(date);
    }

    @Override
    public void insertTrade(Trade trade) {
        SimpleStockDataStorage.addTrade(trade);
    }

    @Override
    public List<Trade> queryAllTrades() {
        return SimpleStockDataStorage.getAllTrades();
    }
}
