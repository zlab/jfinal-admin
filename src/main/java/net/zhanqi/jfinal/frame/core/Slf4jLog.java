package net.zhanqi.jfinal.frame.core;

import com.jfinal.log.Log;
import org.slf4j.LoggerFactory;

public class Slf4jLog extends Log {

    private org.slf4j.Logger log;

    public Slf4jLog(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    public Slf4jLog(String name) {
        log = LoggerFactory.getLogger(name);
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }

    @Override
    public void debug(String message, Throwable t) {
        log.debug(message, t);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void info(String message, Throwable t) {
        log.info(message, t);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }

    @Override
    public void warn(String message, Throwable t) {
        log.warn(message, t);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void error(String message, Throwable t) {
        if (t != null && t instanceof CustomException) {
            return;
        }

        log.error(message, t);
    }

    @Override
    public void fatal(String message) {
        throw new RuntimeException("No Implements");
    }

    @Override
    public void fatal(String message, Throwable t) {
        throw new RuntimeException("No Implements");
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        throw new RuntimeException("No Implements");
    }

}
