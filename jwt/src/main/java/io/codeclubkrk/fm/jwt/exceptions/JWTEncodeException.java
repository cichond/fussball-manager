package io.codeclubkrk.fm.jwt.exceptions;

/**
 * @author darek
 * @since 27.12.2018
 */
public class JWTEncodeException extends RuntimeException {
    public JWTEncodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
