package simplestock.tradeservice.objectmodel.trade.properties;

/**
 * Created by Sandor Nagy on 3/13/16.
 */
public class Prize implements TradeProperty<Integer> {

    private int value;

    private Prize(int value) {
        this.value = value;
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should be one of the predefined values.
     */
    public static Prize create(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: This Prize value is not allowed: " + value);
        }
        return new Prize(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
