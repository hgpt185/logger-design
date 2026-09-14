package implementations;

public class JsonFormatter implements interfaces.LogFormatter {
    @Override
    public String format(models.LogMessage logMessage) {
        return "{ \"timestamp\": \"" + logMessage.getTimestamp() + "\", \"level\": \"" + logMessage.getLevel() + "\", \"message\": \"" + logMessage.getMessage() + "\" }";
    }
    
}
    