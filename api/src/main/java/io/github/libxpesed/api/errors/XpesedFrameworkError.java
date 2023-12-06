package io.github.libxpesed.api.errors;

/**
 * Thrown to indicate that the Xposed framework function is broken.
 */
public class XpesedFrameworkError extends Error {

    public XpesedFrameworkError(String message) {
        super(message);
    }

    public XpesedFrameworkError(String message, Throwable cause) {
        super(message, cause);
    }

    public XpesedFrameworkError(Throwable cause) {
        super(cause);
    }
}
