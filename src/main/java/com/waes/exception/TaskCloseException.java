package com.waes.exception;

/**
 * 任务关闭异常
 * Created by Dell on 2016/5/15.
 */
public class TaskCloseException extends TaskException {

    public TaskCloseException(String message) {
        super(message);
    }

    public TaskCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
