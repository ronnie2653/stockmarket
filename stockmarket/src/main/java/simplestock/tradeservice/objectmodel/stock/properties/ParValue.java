package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Class to hold ParValue value to ensure type safety.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class ParValue implements StockProperty<Integer> {

    private int value;

    private ParValue(int inputValue) {
        this.value = inputValue;
    }

    /**
     * Static factory method for creating ParValue instances.
     *
     * @param value should be non negative Integer.
     */
    public static ParValue create(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: ParValue value should not be negative: " + value);
        }
        return new ParValue(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
