package implementations;

public class PlainTextFormatter implements interfaces.LogFormatter {
    @Override
    public String format(models.LogMessage logMessage) {
        return "[" + logMessage.getTimestamp() + "] " + logMessage.getLevel() + ": " + logMessage.getMessage();
    }
    
}
