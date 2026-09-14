package implementations.LogHandlers;

import enums.LogLevel;

public class DebugHandler extends abstracts.LogHandler {

    @Override
    protected boolean shouldHandle(models.LogMessage logMessage) {
        return logMessage.getLevel().equals(LogLevel.DEBUG);
    }

}
