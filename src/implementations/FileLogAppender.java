package implementations;

import interfaces.LogFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileLogAppender implements interfaces.LogAppender {

    private final LogFormatter formatter;
    private final BufferedWriter writer;
    private final BlockingQueue<models.LogMessage> queue;

    private final Thread writerThread;
    private volatile boolean running = true;

    public FileLogAppender(LogFormatter formatter, String filePath) throws IOException {
        this.formatter = formatter;
        this.writer = new BufferedWriter(new FileWriter(filePath, true));
        this.queue = new LinkedBlockingQueue<>();

        this.writerThread = new Thread(this::processLogs, "file-log-writer");
        this.writerThread.start();
    }

    @Override
    public void append(models.LogMessage logMessage) {
        try {
            queue.put(logMessage);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while adding log to queue", e);
        }
    }

    private void processLogs() {
        try {
            while (running || !queue.isEmpty()) {

                models.LogMessage logMessage = queue.take();

                writer.write(formatter.format(logMessage));
                writer.newLine();
                writer.flush();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            throw new RuntimeException("Error writing logs to file", e);
        }
    }

    public void close() {
        running = false;

        writerThread.interrupt();

        try {
            writerThread.join();
            writer.close();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while closing logger", e);
        } catch (IOException e) {
            throw new RuntimeException("Error closing log file", e);
        }
    }
}

