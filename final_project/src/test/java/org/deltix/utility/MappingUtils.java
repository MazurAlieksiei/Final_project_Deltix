package org.deltix.utility;

import org.apache.log4j.Logger;
import org.deltix.constants.CommonConstants;

public class MappingUtils {

    private static Logger log = Logger.getLogger(MappingUtils.class);

    public static String mapHeaderTitleToCellId(String title) {
        log.info("Get column header title by cell id.");
        return CommonConstants.headerColumnsTitles.get(title);
    }
}
