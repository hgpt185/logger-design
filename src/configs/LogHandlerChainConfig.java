package configs;

import abstracts.LogHandler;
import enums.LogLevel;
import interfaces.LogAppender;

public class LogHandlerChainConfig {
    private static final LogHandler debugHandler= new implementations.LogHandlers.DebugHandler();
    private static final LogHandler infoHandler = new implementations.LogHandlers.InfoHandler();
    private static final LogHandler errorHandler = new implementations.LogHandlers.ErrorHandler();

    public static LogHandler buildLogHandlerChain() {
        debugHandler.setNextHandler(infoHandler);
        infoHandler.setNextHandler(errorHandler);
        return debugHandler;
    }

    public static void appendForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debugHandler.addObserver(appender);
            case INFO -> infoHandler.addObserver(appender);
            case ERROR -> errorHandler.addObserver(appender);
            default -> throw new IllegalArgumentException("Unsupported log level: " + level);
        }
    }
    
}
