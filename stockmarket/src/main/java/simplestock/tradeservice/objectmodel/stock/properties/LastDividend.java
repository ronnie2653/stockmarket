package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Class to hold LastDividend value to ensure type safety.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class LastDividend implements StockProperty<Integer> {

    private int value;

    private LastDividend(int value) {
        this.value = value;
    }

    /**
     * Static factory method for creating LastDividend instances.
     *
     * @param value should be non negative Integer.
     */
    public static LastDividend create(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: LastDividend value should not be negative: " + value);
        }
        return new LastDividend(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
