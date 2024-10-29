package com.senla.ecosystem.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import lombok.experimental.UtilityClass;
import org.slf4j.LoggerFactory;

/**
 * Utility class for setting up logging configuration.
 * This class is responsible for initializing the logging system
 * with a specified log file name and configuring the log output format.
 */
@UtilityClass
public class LoggerManager {

    /**
     * Sets up the logger with a file appender.
     *
     * @param logFileName the name of the log file where log messages will be saved.
     *                    The log file will be created if it does not exist,
     *                    and new messages will be appended to it.
     */
    public static void setupLogger(String logFileName) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        fileAppender.setName("FILE");
        fileAppender.setFile(logFileName);
        fileAppender.setAppend(true);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n");
        encoder.start();

        fileAppender.setEncoder(encoder);
        fileAppender.start();

        loggerContext.getLogger("ROOT").addAppender(fileAppender);
    }
}
