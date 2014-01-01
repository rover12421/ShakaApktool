package com.rover12421.shaka.apktool.util;

/**
 * Created by rover12421 on 12/29/13.
 */
public class ShakaRuntimeException extends RuntimeException {
    public ShakaRuntimeException(Throwable cause) {
        super(cause);
    }

    public ShakaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShakaRuntimeException(String message) {
        super(message);
    }

    public ShakaRuntimeException() {
    }
}
