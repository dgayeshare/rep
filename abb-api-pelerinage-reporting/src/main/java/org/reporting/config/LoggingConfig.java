package org.reporting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;

@Configuration
@EnableAspectJAutoProxy
public class LoggingConfig {

    @Value("${logging.api.path}")
    private String apiLogsPath;

    @Bean
    public Logger apiLogger() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Créer un nouvel appender pour les logs d'API
        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(loggerContext);
        fileAppender.setName("API_FILE");
        fileAppender.setFile(apiLogsPath + "/api.log");

        // Définir la politique de rotation des fichiers
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setContext(loggerContext);
        rollingPolicy.setParent(fileAppender);
        rollingPolicy.setFileNamePattern(apiLogsPath + "/api-%d{yyyy-MM-dd}.%i.log");
        rollingPolicy.setMaxFileSize(FileSize.valueOf("10MB"));
        rollingPolicy.setMaxHistory(30);
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("3GB"));
        rollingPolicy.start();

        // Configurer l'encodeur pour le format des logs
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        encoder.start();

        // Associer l'encodeur et la politique de rotation à l'appender
        fileAppender.setEncoder(encoder);
        fileAppender.setRollingPolicy(rollingPolicy);
        fileAppender.start();

        // Récupérer ou créer le logger pour les API
        Logger apiLogger = loggerContext.getLogger("API_LOGS");
        apiLogger.setAdditive(false);  // Éviter la duplication des logs
        apiLogger.addAppender(fileAppender);

        return apiLogger;
    }
}