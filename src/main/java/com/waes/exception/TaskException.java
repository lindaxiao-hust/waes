package com.waes.exception;

/**
 * 任务相关业务异常
 * Created by Dell on 2016/5/15.
 */
public class TaskException extends RuntimeException {

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
