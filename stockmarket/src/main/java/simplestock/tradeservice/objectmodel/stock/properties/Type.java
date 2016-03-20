package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Class to hold Type values to ensure type safety.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public class Type implements StockProperty<String> {
    // A type value can be either Common or Preferred.
    public static final String Common = "Common";
    public static final String Preferred = "Preferred";

    private String value;

    public Type(String inputValue) {

        value = inputValue;
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should be one of the predefined values.
     */
    public static Type create(String value) {
        if (!(Common.equals(value) || Preferred.equals(value))) {
            throw new IllegalArgumentException("Error: This Type value is not allowed: " + value);
        }
        return new Type(value);
    }

    @Override
    public String getValue() {
        return value;
    }

}
