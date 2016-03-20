package simplestock.tradeservice.objectmodel.trade.properties;

/**
 * Created by Sandor Nagy on 3/13/16.
 */
public class BuyOrSell implements TradeProperty<String> {

    public static final String Buy = "Buy";
    public static final String Sell = "Sell";

    private final String value;

    private BuyOrSell(String value) {
        this.value = value;
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should be one of the predefined values.
     */
    public static BuyOrSell create(String value) {
        if (!(Buy.equals(value) || Sell.equals(value))) {
            throw new IllegalArgumentException("Error: This BuyOrSell value is not allowed: \" + value");
        }
        return new BuyOrSell(value);
    }


    @Override
    public String getValue() {
        return value;
    }

}
