package org.deltix.constants;

import java.util.Map;

public class CommonConstants {

    public static Map<String, String> headerColumnsTitles = Map.ofEntries(
            Map.entry("Id", "orderId"),
            Map.entry("End time", "endTime"),
            Map.entry("Avg fill price", "averageFillPrice"),
            Map.entry("Num of executions", "numberOfExecutions"));
}
