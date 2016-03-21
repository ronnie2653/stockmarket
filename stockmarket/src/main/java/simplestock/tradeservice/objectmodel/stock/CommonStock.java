package simplestock.tradeservice.objectmodel.stock;

import simplestock.tradeservice.objectmodel.stock.properties.LastDividend;
import simplestock.tradeservice.objectmodel.stock.properties.ParValue;
import simplestock.tradeservice.objectmodel.stock.properties.Symbol;
import simplestock.tradeservice.objectmodel.stock.properties.Type;

/**
 * Class for PreferredStock objects where FixedDividend is not present.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class CommonStock extends Stock {


    private CommonStock(Symbol symbol,
                        Type type,
                        LastDividend lastDividend,
                        ParValue parValue) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    /**
     * Builder Pattern implementation to create Common stock.
     */
    public static class CommonStockBuilder {
        private Symbol symbol;
        private Type type;
        private LastDividend lastDividend;
        private ParValue parValue;

        public CommonStockBuilder(Symbol symbol, Type type) {
            if (symbol == null) {
                throw new IllegalArgumentException("Error: inputStockSymbol should not be null");
            }
            if (type == null) {
                throw new IllegalArgumentException("Error: inputType should not be null");
            }
            this.symbol = symbol;
            this.type = type;
        }

        public CommonStockBuilder lastDividend(LastDividend lastDividend) {
            if (lastDividend == null) {
                throw new IllegalArgumentException("Error: inputLastDividend should not be null");
            }
            this.lastDividend = lastDividend;
            return this;
        }

        public CommonStockBuilder parValue(ParValue parValue) {
            if (parValue == null) {
                throw new IllegalArgumentException("Error: inputParValue should not be null");
            }
            this.parValue = parValue;
            return this;
        }

        public Stock create() {
            if (Type.Common.equals(type.getValue())) {
                return new CommonStock(symbol, type, lastDividend, parValue);
            } else {
                throw new IllegalArgumentException("Error: Cannot create this Stock type: " + type);
            }
        }
    }

}
