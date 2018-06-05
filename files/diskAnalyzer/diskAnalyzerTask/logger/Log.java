package company.animals.logger;

import org.apache.log4j.Logger;

public class Log {

    public static final Logger log = Logger.getLogger(Log.class);

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void trace(String message) {
        log.trace(message);
    }


}
