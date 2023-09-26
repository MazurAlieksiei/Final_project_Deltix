package org.example.utility;

import java.util.List;

public class StringUtils {


    public static String getHexColorValueFromRGB(String rgbValue) {
        List<String> test = List.of(rgbValue.replaceAll("[a-zA-Z]", "").
                replaceAll("^\\(|\\)$", "").
                replaceAll(" ", "").
                split("\\,"));
        return String.format(
                "#%02x%02x%02x",
                Integer.valueOf(test.get(0)),
                Integer.valueOf(test.get(1)),
                Integer.valueOf(test.get(2)));
    }

    public static String getHexColorValueFROMRGBA(String rgbaValue) {
        List<String> test = List.of(rgbaValue.replaceAll("[a-zA-Z]", "").
                replaceAll("^\\(|\\)$", "").
                replaceAll(" ", "").
                split("\\,"));
        return String.format(
                "#%02x%02x%02x%02x",
                Integer.parseInt(test.get(0)),
                Integer.parseInt(test.get(1)),
                Integer.parseInt(test.get(2)),
                Integer.parseInt(test.get(3)));
    }
}
