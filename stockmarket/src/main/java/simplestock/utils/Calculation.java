package simplestock.utils;

import java.math.BigDecimal;

/**
 * Utility class for static utility methods.
 * <p>
 * Created by Sandor Nagy on 3/20/16.
 */
public class Calculation {

    /**
     * This utility method will return the nth root of the input BigDecimal.
     */
    static public BigDecimal nthRoot(BigDecimal bigDecimal, int num) {

        return new BigDecimal(Math.pow(bigDecimal.doubleValue(), 1.0 / num));

    }

}

