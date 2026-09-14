package implementations.LogHandlers;

import abstracts.LogHandler;

public class InfoHandler extends LogHandler {
    @Override
     public boolean shouldHandle(models.LogMessage logMessage) {
        return logMessage.getLevel().equals(enums.LogLevel.INFO);
    }
    
}
