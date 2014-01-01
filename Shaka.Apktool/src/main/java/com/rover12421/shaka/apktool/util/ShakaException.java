package com.rover12421.shaka.apktool.util;

/**
 * Created by rover12421 on 12/29/13.
 */
public class ShakaException extends Exception {
    public ShakaException(Throwable cause) {
        super(cause);
    }

    public ShakaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShakaException(String message) {
        super(message);
    }

    public ShakaException() {
    }
}
