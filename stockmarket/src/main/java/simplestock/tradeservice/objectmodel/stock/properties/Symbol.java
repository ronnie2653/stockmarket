package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Class to hold Symbol values to ensure type safety.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class Symbol implements StockProperty<String> {

    private String value;

    private Symbol(String inputValue) {

        this.value = inputValue.toUpperCase();
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should have length of 3
     */
    public static Symbol create(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Error: Symbol value should not be null!");
        }
        if (!(value.length() == 3)) {
            throw new IllegalArgumentException("Error: Symbol value length should be 3!");
        }

        return new Symbol(value);
    }

    @Override
    public String getValue() {
        return value;
    }

}
