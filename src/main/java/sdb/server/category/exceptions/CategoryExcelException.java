package sdb.server.category.exceptions;

public class CategoryExcelException extends RuntimeException{

    public CategoryExcelException() {
    }

    public CategoryExcelException(String message) {
        super(message);
    }

    public CategoryExcelException(Throwable cause) {
        super(cause);
    }

    public CategoryExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryExcelException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
