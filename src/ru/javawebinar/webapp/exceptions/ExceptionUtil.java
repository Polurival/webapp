package ru.javawebinar.webapp.exceptions;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;

/**
 * GKislin
 * http://javawebinar.ru/basejava/
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static WebAppException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new WebAppException(ExceptionType.ALREADY_EXISTS, e);
            }
        }
        return new WebAppException(ExceptionType.SQL_ERROR, e);
    }
}
