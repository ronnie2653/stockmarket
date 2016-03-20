package simplestock.tradeservice.objectmodel.stock;

import simplestock.tradeservice.objectmodel.stock.properties.LastDividend;
import simplestock.tradeservice.objectmodel.stock.properties.ParValue;
import simplestock.tradeservice.objectmodel.stock.properties.Symbol;
import simplestock.tradeservice.objectmodel.stock.properties.Type;

/**
 * Common type for Stocks with the properties that every stock has.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public abstract class Stock {

    protected Symbol symbol;
    protected Type type;
    protected LastDividend lastDividend;
    protected ParValue parValue;

    public Symbol getSymbol() {
        return symbol;
    }

    public Type getType() {
        return type;
    }

    public LastDividend getLastDividend() {
        return lastDividend;
    }

    public ParValue getParValue() {
        return parValue;
    }

}
