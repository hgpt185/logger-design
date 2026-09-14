# Logger Design

A small Java logging library built as a design exercise. It uses a chain of responsibility for log levels, pluggable formatters, and pluggable appenders.

## What it does

- Singleton `Logger` with `info`, `debug`, and `error`
- Level handlers (`DEBUG` -> `INFO` -> `ERROR`) that decide whether to process a message
- Appenders for console and file
- Formatters for plain text and JSON
- Thread-safe appenders so concurrent callers can log without interleaving

## Layout

```
src/
  App.java
  abstracts/LogHandler.java
  configs/LogHandlerChainConfig.java
  enums/LogLevel.java
  interfaces/LogAppender.java
  interfaces/LogFormatter.java
  models/LogMessage.java
  implementations/
    ConsoleLogAppender.java
    FileLogAppender.java
    JsonFormatter.java
    PlainTextFormatter.java
    LogHandlers/
      DebugHandler.java
      InfoHandler.java
      ErrorHandler.java
  service/Logger.java
```

## Usage

```java
Logger logger = Logger.getInstance();

LogHandlerChainConfig.appendForLevel(
        LogLevel.INFO,
        new ConsoleLogAppender(new PlainTextFormatter())
);

LogHandlerChainConfig.appendForLevel(
        LogLevel.ERROR,
        new FileLogAppender(new PlainTextFormatter(), "logs.txt")
);

logger.info("startup complete");
logger.error("something failed");
```

`App.java` is a small concurrent demo that writes from a thread pool.

## Run

Open the folder in VS Code / Cursor with the Java extension, or compile from `src/` into `bin/` and run `App`.
