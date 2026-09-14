package implementations.LogHandlers;

import abstracts.LogHandler;

public class ErrorHandler extends LogHandler {
    @Override
    public boolean shouldHandle(models.LogMessage logMessage) {
        return logMessage.getLevel().equals(enums.LogLevel.ERROR);
    }
    
}
