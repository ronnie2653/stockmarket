package simplestock.tradeservice.objectmodel.trade;

import simplestock.tradeservice.objectmodel.stock.Stock;
import simplestock.tradeservice.objectmodel.trade.properties.BuyOrSell;
import simplestock.tradeservice.objectmodel.trade.properties.Prize;
import simplestock.tradeservice.objectmodel.trade.properties.Quantity;
import simplestock.tradeservice.objectmodel.trade.properties.TimeStamp;

/**
 * Class that defines a trade entity.
 * <p>
 * Created by Sandor Nagy on 3/13/16.
 */
public class Trade {

    private TimeStamp time;
    private Quantity quantity;
    private BuyOrSell buyOrSell;
    private Prize prize;
    private Stock stock;

    private Trade(TimeStamp time,
                  Stock stock,
                  Quantity quantity,
                  BuyOrSell buyOrSell,
                  Prize prize) {
        this.time = time;
        this.stock = stock;
        this.buyOrSell = buyOrSell;
        this.prize = prize;
        this.quantity = quantity;
    }

    // Copy constructor
    public Trade(Trade trade) {
        this(trade.getTime(), trade.getStock(), trade.getQuantity(), trade.getBuyOrSell(), trade.getPrize());
    }

    public TimeStamp getTime() {
        return time;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public BuyOrSell getBuyOrSell() {
        return buyOrSell;
    }

    public Prize getPrize() {
        return prize;
    }

    public Stock getStock() {
        return stock;
    }

    /**
     * Using the Builder Pattern to create Trades.
     */
    public static class TradeBuilder {
        private Stock stock;
        private Quantity quantity;
        private BuyOrSell buyOrSell;
        private Prize prize;

        public TradeBuilder(Stock stock) {
            if (stock == null) {
                throw new IllegalArgumentException("Error: stock should not be null");
            }
            this.stock = stock;
        }

        public TradeBuilder quantity(Quantity quantity) {
            if (quantity == null) {
                throw new IllegalArgumentException("Error: quantity should not be null");
            }
            this.quantity = quantity;
            return this;
        }

        public TradeBuilder buyOrSell(BuyOrSell buyOrSell) {
            if (buyOrSell == null) {
                throw new IllegalArgumentException("Error: buyOrSell should not be null");
            }
            this.buyOrSell = buyOrSell;
            return this;
        }

        public TradeBuilder prize(Prize prize) {
            if (prize == null) {
                throw new IllegalArgumentException("Error: prize should not be null");
            }
            this.prize = prize;
            return this;
        }

        /**
         * Create a Trade object with the current time inserted implicitly.
         */
        public Trade create() {
            TimeStamp time = TimeStamp.create(System.currentTimeMillis());
            return new Trade(time, stock, quantity, buyOrSell, prize);
        }
    }

}
