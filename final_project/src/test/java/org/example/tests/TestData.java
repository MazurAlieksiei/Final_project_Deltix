package org.example.tests;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class TestData {

    @DataProvider(name = "axeValuesProvider")
    public Object[][] createData() {
        return new Object[][]{
                {
                        "Avg fill price", "Avg fill price",
                        Arrays.asList("5", "10", "15", "20", "25", "30", "35", "40", "45"),
                        Arrays.asList("0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50")
                },
                {
                        "Exec size", "Exec size",
                        Arrays.asList("0", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26"),
                        Arrays.asList("0", "5", "10", "15", "20", "25")
                },
                {
                        "Num of executions", "Num of executions",
                        Arrays.asList("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"),
                        Arrays.asList("0", "20", "40", "60", "80", "100", "120", "140", "160")
                },
                {
                        "Price", "Price",
                        Arrays.asList("0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"),
                        Arrays.asList("0", "10", "20", "30", "40", "50")
                },
                {
                        "Size", "Size",
                        Arrays.asList("0", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26"),
                        Arrays.asList("0", "5", "10", "15", "20", "25")
                },
                {
                        "Execution price volatility", "Execution price volatility",
                        Arrays.asList("0", "0.02", "0.04", "0.06", "0.08", "0.1", "0.12", "0.14", "0.16", "0.18", "0.2", "0.22", "0.24", "0.26", "0.28", "0.3", "0.32"),
                        Arrays.asList("0", "0.05", "0.1", "0.15", "0.2", "0.25", "0.3")
                },
                {
                        "Permanent market impact", "Permanent market impact",
                        Arrays.asList("-1", "-0.80", "-0.60", "-0.40", "-0.20", "0", "0.2", "0.4", "0.6", "0.8", "1"),
                        Arrays.asList("-1.20", "-1", "-0.80", "-0.60", "-0.40", "-0.20", "0", "0.2", "0.4", "0.6", "0.8", "1", "1.2")
                },
                {
                        "Realized market impact", "Realized market impact",
                        Arrays.asList("-1", "-0.80", "-0.60", "-0.40", "-0.20", "0", "0.2", "0.4", "0.6", "0.8", "1"),
                        Arrays.asList("-1.20", "-1", "-0.80", "-0.60", "-0.40", "-0.20", "0", "0.2", "0.4", "0.6", "0.8", "1", "1.2")
                },
                {
                        "Participation rate", "Participation rate",
                        Arrays.asList("0", "0.2", "0.4", "0.6", "0.8", "1", "1.2", "1.4", "1.6", "1.8", "2"),
                        Arrays.asList("-0.20", "0", "0.2", "0.4", "0.6", "0.8", "1", "1.2", "1.4", "1.6", "1.8", "2", "2.2")
                },
                {
                        "Currency code", "Currency code",
                        Arrays.asList("988", "990", "992", "994", "996", "998", "1,000", "1,002", "1,004", "1,006", "1,008", "1,010"),
                        Arrays.asList("988", "990", "992", "994", "996", "998", "1,000", "1,002", "1,004", "1,006", "1,008", "1,010")
                },
                {
                        "Sequence number", "Sequence number",
                        Arrays.asList("0", "200", "400", "600", "800", "1,000", "1,200", "1,400", "1,600", "1,800", "2,000", "2,200", "2,400", "2,600", "2,800", "3,000"),
                        Arrays.asList("0", "500", "1,000", "1,500", "2,000", "2,500", "3,000")
                },
                {
                        "Db sequence number", "Db sequence number",
                        Arrays.asList("605,800", "606,000", "606,200", "606,400", "606,600", "606,800", "607,000", "607,200", "607,400", "607,600", "607,800", "608,000", "608,200", "608,400", "608,600", "608,800", "609,000"),
                        Arrays.asList("605,500", "606,000", "606,500", "607,000", "607,500", "608,000", "608,500", "609,000")
                },
                {
                        "Benchmark price", "Benchmark price",
                        Arrays.asList("0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"),
                        Arrays.asList("0", "5", "10", "5", "20", "25", "30", "35", "40", "45", "50")
                },

                {
                        "Shortfall", "Shortfall",
                        null,
                        null
                },
                {
                        "Multiplier", "Multiplier",
                        null,
                        null
                },
                {
                        "Tick size", "Tick size",
                        null,
                        null
                },
                {
                        "Price imp. (ticks)", "Price imp. (ticks)",
                        null,
                        null
                },
                {
                        "Price imp. (amount)", "Price imp. (amount)",
                        null,
                        null
                }
        };
    }

    @DataProvider(name = "columnsNameProvider")
    public Object[][] createData2() {
        return new Object[][]{
                {"Avg fill price"},
                {"Num of executions"},
                {"Id"},
                {"End time"}
        };
    }
}
