package service;

import configs.LogHandlerChainConfig;
import abstracts.LogHandler;

public class Logger {

    private static Logger logger = new Logger();
    
    private LogHandler logHandlerChain;

    private Logger() {
        logHandlerChain = LogHandlerChainConfig.buildLogHandlerChain();
    }

    public static Logger getInstance() {
        return logger;
    }

    public static void info(String message) {
        log(message, enums.LogLevel.INFO);
    }

    public static void debug(String message) {
        log(message, enums.LogLevel.DEBUG);
    }

    public static void error(String message) {
        log(message, enums.LogLevel.ERROR);
    }

    private static void log(String message, enums.LogLevel level) {
        models.LogMessage logMessage = new models.LogMessage(message, level, new java.sql.Timestamp(System.currentTimeMillis()));
        logger.logHandlerChain.handle(logMessage);
    }

}
