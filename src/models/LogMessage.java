package models;

import java.sql.Timestamp;
import enums.LogLevel;

public class LogMessage {
    private String message;
    private LogLevel level;
    private Timestamp timestamp;

    public LogMessage(String message, LogLevel level, Timestamp timestamp) {
        this.message = message;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
