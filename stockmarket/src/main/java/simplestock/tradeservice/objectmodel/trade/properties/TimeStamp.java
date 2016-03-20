package simplestock.tradeservice.objectmodel.trade.properties;


/**
 * Wrapper class for TimeStamp values. Values means milli seconds that have elapsed since January 1, 1970.
 * <p>
 * Created by Sandor Nagy on 3/13/16.
 */
public class TimeStamp implements TradeProperty<Long> {

    private long value;

    private TimeStamp(long value) {
        this.value = value;
    }

    /**
     * Static factory method for creating Type instances.
     *
     * @param value should be one of the predefined values.
     */
    public static TimeStamp create(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: This TimeStamp value is not allowed: " + value);
        }
        return new TimeStamp(value);
    }

    @Override
    public Long getValue() {
        return value;
    }

}
