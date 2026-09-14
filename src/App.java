import enums.LogLevel;
import implementations.PlainTextFormatter;
import service.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import configs.LogHandlerChainConfig;
import implementations.ConsoleLogAppender;
import implementations.FileLogAppender;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getInstance();

        LogHandlerChainConfig.appendForLevel(
                LogLevel.INFO,
                new ConsoleLogAppender(new PlainTextFormatter())
        );

        LogHandlerChainConfig.appendForLevel(
                LogLevel.ERROR,
                new ConsoleLogAppender(new PlainTextFormatter())
        );

        LogHandlerChainConfig.appendForLevel(
                LogLevel.ERROR,
                new FileLogAppender(new PlainTextFormatter(), "logs.txt")
        );

        // Usage
        // logger.info("This is some key information"); // CONSOLE
        // logger.error("Oh no! there's an error"); // CONSOLE + FILE

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        for(int i = 0; i < 15; i++) {
            final int index = i;
            executorService.submit(() -> {
                logger.error("Info message from thread " + index);
            });
        }
    }
}
