package com.waes.exception;

/**
 * 重复检测异常（运行期异常）
 * Created by Dell on 2016/5/15.
 */
public class RepeatCheckException extends TaskException{

    public RepeatCheckException(String message) {
        super(message);
    }

    public RepeatCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
