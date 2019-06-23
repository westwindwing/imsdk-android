package com.qunar.im.base.common;

public class ExceptionEvent {
    public Throwable throwable;

    public ExceptionEvent(Throwable throwable) {
        this.throwable = throwable;
    }
}
