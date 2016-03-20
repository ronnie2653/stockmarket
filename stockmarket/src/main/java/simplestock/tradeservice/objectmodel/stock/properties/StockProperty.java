package simplestock.tradeservice.objectmodel.stock.properties;

/**
 * Common base type for stock properties.
 * In this way we can ensure type safety.
 * <p>
 * Created by Sandor Nagy on 3/12/16.
 */
public interface StockProperty<T> {

    T getValue();

}

