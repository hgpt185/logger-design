package interfaces;

import models.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
