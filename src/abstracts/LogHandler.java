package abstracts;

import interfaces.LogFormatter;
import interfaces.LogAppender;
import models.LogMessage;
import java.util.List;

public abstract class LogHandler {
    private LogHandler nextHandler;

    private final List<LogAppender> oberservs = new java.util.concurrent.CopyOnWriteArrayList<>();

    public LogHandler() {
        this.nextHandler = null;
    }

    public void setNextHandler(LogHandler nextHandler) {
        if (this.nextHandler != null) {
            throw new IllegalStateException("Next handler is already set.");
        }
        this.nextHandler = nextHandler;
    }

    public void addObserver(LogAppender observer) {
        oberservs.add(observer);
    }

    public void removeObserver(LogAppender observer) {
        oberservs.remove(observer);
    }

    protected void notifyObservers(LogMessage logMessage) {
        for (LogAppender observer : oberservs) {
            observer.append(logMessage);
        }
    }

    protected abstract boolean shouldHandle(LogMessage logMessage);

    public void handle(LogMessage logMessage) {
        if (shouldHandle(logMessage)) {
            notifyObservers(logMessage);
        } else if (nextHandler != null) {
            nextHandler.handle(logMessage);
        }
    }

}
