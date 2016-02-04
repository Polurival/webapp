package ru.javawebinar.webapp.exceptions;

/**
 * GKislin
 * 09.10.2015.
 */
public class WebAppException extends RuntimeException {
    private final String uuid;
    private ExceptionType type;

    public WebAppException(ExceptionType type, String uuid) {
        this(type, uuid, null);
    }

    public WebAppException(ExceptionType type, Exception e) {
        this(type, null, e);
    }

    public WebAppException(ExceptionType type) {
        this(type, null, null);
    }

    public WebAppException(ExceptionType type, String uuid, Exception e) {
        super(type.getMessage(), e);
        this.type = type;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public ExceptionType getType() {
        return type;
    }
}
