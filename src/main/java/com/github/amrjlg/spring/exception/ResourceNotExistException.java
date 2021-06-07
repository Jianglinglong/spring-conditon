package com.github.amrjlg.spring.exception;

/**
 * @author amrjlg
 * 2019/8/22
 * 16:24
 */
public class ResourceNotExistException extends RuntimeException {
    public ResourceNotExistException(String message) {
        super(message);
    }
}
