package simplestock.tradeservice.objectmodel.stock;

import simplestock.tradeservice.objectmodel.stock.properties.*;

/**
 * Class for PreferredStock objects where FixedDividend is present.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class PreferredStock extends Stock {

    // FixedDividend is a unique property of a PreferredStock
    private FixedDividend fixedDividend;

    private PreferredStock(Symbol symbol,
                           Type type,
                           LastDividend lastDividend,
                           FixedDividend fixedDividend,
                           ParValue parValue) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }


    public FixedDividend getFixedDividend() {
        return fixedDividend;
    }

    /**
     * Using the Builder Pattern to create Preferred stock.
     * Exception thrown here not from constructor of PreferredStock to avoid the possibility of Finalizer Attack.
     */
    public static class PreferredStockBuilder {
        private Symbol symbol;
        private Type type;
        private LastDividend lastDividend;
        private FixedDividend fixedDividend;
        private ParValue parValue;

        public PreferredStockBuilder(Symbol symbol, Type type) {
            if (symbol == null) {
                throw new IllegalArgumentException("Error: stockSymbol should not be null");
            }
            if (type == null) {
                throw new IllegalArgumentException("Error: type should not be null");
            }
            this.symbol = symbol;
            this.type = type;
        }

        public PreferredStockBuilder lastDividend(LastDividend lastDividend) {
            if (lastDividend == null) {
                throw new IllegalArgumentException("Error: lastDividend should not be null");
            }
            this.lastDividend = lastDividend;
            return this;
        }

        public PreferredStockBuilder fixedDividend(FixedDividend fixedDividend) {
            if (fixedDividend == null) {
                throw new IllegalArgumentException("Error: inputLastFixedDividend should not be null");
            }
            this.fixedDividend = fixedDividend;
            return this;
        }

        public PreferredStockBuilder parValue(ParValue parValue) {
            if (parValue == null) {
                throw new IllegalArgumentException("Error: inputParValue should not be null");
            }
            this.parValue = parValue;
            return this;
        }

        public PreferredStock create() {
            if (Type.Preferred.equals(type.getValue())) {
                return new PreferredStock(symbol, type, lastDividend, fixedDividend, parValue);
            } else {
                throw new IllegalArgumentException("Error: Cannot create this Stock type: " + type);
            }

        }
    }

}
