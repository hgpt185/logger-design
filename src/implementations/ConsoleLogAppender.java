package implementations;

import interfaces.LogFormatter;

public class ConsoleLogAppender implements interfaces.LogAppender {

    private final LogFormatter formatter;

    public ConsoleLogAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(models.LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }
    
}
