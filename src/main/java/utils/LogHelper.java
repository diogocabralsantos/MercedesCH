package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHelper {

    // Method to dynamically set the log file name for each test
    public static void setLogFileName() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String logFileName = "logs/testlog_" + timestamp + ".log";  // Include timestamp in file name

        // Get the LoggerContext
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        // Create a new layout for the RollingFileAppender
        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n")
                .build();

        // Create a new RollingFileAppender with the required policies
        RollingFileAppender appender = RollingFileAppender.newBuilder()
                .setName("FileAppender")
                .setLayout(layout)
                .withFileName(Paths.get(logFileName).toAbsolutePath().toString())
                .withFilePattern("logs/test-%d{yyyy-MM-dd}-%i.log")
                .withPolicy(TimeBasedTriggeringPolicy.newBuilder().withInterval(1).build())  // Roll over every day
                .withStrategy(DefaultRolloverStrategy.newBuilder().build())  // Default rollover strategy
                .build();

        if (appender != null) {
            appender.start();
        } else {
            throw new RuntimeException("RollingFileAppender is null!");
        }

        // Get the root logger configuration
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

        // Remove the old appender if it exists
        if (loggerConfig.getAppenders().containsKey("FileAppender")) {
            loggerConfig.removeAppender("FileAppender");
        }

        // Add the new appender to the logger configuration
        loggerConfig.addAppender(appender, null, null);

        // Update the logging context to apply changes
        context.updateLoggers();
    }

    // Method to retrieve logger for a class
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    // Method to log information messages
    public static void logInfo(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    // Method to log warning messages
    public static void logWarn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    // Method to log error messages
    public static void logError(Class<?> clazz, String message, Throwable error) {
        getLogger(clazz).error(message, error);
    }
}
