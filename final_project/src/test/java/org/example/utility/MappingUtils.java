package org.example.utility;

import org.example.constants.CommonConstants;

public class MappingUtils {

    public static String mapHeaderTitleToCellId(String title) {
        return CommonConstants.headerColumnsTitles.get(title);
    }
}
