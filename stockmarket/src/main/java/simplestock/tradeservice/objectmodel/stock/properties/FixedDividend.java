package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Class to hold FixedDividend value to ensure type safety.
 * This is a % value stored as an int but returned as divided by 100.
 * <p>
 * e.g.: new FixedDividend(2).getValue() => 0.02.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class FixedDividend implements StockProperty<Double> {

    private int value;

    private FixedDividend(int value) {
        this.value = value;
    }

    /**
     * Static factory method for creating FixedDividend instances.
     *
     * @param value should be non negative Integer.
     */
    public static FixedDividend create(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: FixedDividend value should not be negative: " + value);
        }
        return new FixedDividend(value);
    }


    /**
     * Returns the value of this FixedDividend divided by 100, because FixedDividend is a percentage value.
     */
    @Override
    public Double getValue() {
        return (value / 100.0);
    }

}
