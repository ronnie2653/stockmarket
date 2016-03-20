package simplestock.tradeservice.objectmodel.trade.properties;

/**
 * Created by Sandor Nagy on 3/13/16.
 */
public class Quantity implements TradeProperty<Integer> {

    private int value;

    private Quantity(int value) {
        this.value = value;
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should be one of the predefined values.
     */
    public static Quantity create(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: This Quantity value is not allowed: " + value);
        }
        return new Quantity(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
