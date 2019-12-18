package com.github.jianglinglong.spring.exception;

/**
 * @author jiang
 * @date 2019/8/22
 * @time 16:24
 */
public class ResourceNotExistException extends RuntimeException {
    public ResourceNotExistException(String message) {
        super(message);
    }
}
