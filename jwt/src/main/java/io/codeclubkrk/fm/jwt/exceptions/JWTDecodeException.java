package io.codeclubkrk.fm.jwt.exceptions;

/**
 * @author darek
 * @since 27.12.2018
 */
public class JWTDecodeException extends RuntimeException {
    public JWTDecodeException(String message) {
        super(message);
    }

    public JWTDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
