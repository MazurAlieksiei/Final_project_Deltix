package org.example.utility;

import com.google.common.collect.Ordering;
import org.example.enums.Order;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CollectionUtils {

    public static <T extends Comparable> boolean isSortedInRequiredWay(List<T> list, Order order) throws NoSuchAlgorithmException {
        switch (order) {
            case ASC:
                return Ordering.natural().isOrdered(list);
            case DESC:
                return Ordering.natural().reverse().isOrdered(list);
            default:
                throw new NoSuchAlgorithmException("No such order type.");
        }
    }
}
