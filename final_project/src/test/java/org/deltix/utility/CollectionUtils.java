package org.deltix.utility;

import com.google.common.collect.Ordering;
import org.apache.log4j.Logger;
import org.deltix.enums.Order;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CollectionUtils {

    private static Logger log = Logger.getLogger(CollectionUtils.class);

    public static <T extends Comparable> boolean isSortedInRequiredWay(List<T> list, Order order) throws NoSuchAlgorithmException {
        log.info("Check if data sorted in required way.");
        switch (order) {
            case ASC:
                return Ordering.natural().isOrdered(list);
            case DESC:
                return Ordering.natural().reverse().isOrdered(list);
            default:
                log.error("Given sorting order not exist.");
                throw new NoSuchAlgorithmException("No such order type.");
        }
    }
}
