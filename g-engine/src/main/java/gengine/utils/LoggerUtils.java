package gengine.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    private static Logger log = LoggerFactory.getLogger(LoggerUtils.class);

    public static void info(String msg) {
        log.info(msg);
    }
}
