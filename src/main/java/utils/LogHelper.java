package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    // Metodo genérico para retornar um Logger associado a uma classe
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    // Metodo para registar uma mensagem de info
    public static void logInfo(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    // Metodo para registar uma mensagem de warning
    public static void logWarn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    // Metodo para registar um erro
    public static void logError(Class<?> clazz, String message, Throwable error) {
        getLogger(clazz).error(message, error);
    }

    // Metodo para registar um debug
    public static void logDebug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }
}
