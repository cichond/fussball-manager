package io.codeclubkrk.fm.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.codeclubkrk.fm.jwt.exceptions.JWTDecodeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * @author darek
 * @since 27.12.2018
 */
public final class JWTDecoder implements JWT {

    private final Map<String, String> headerClaims;
    private final Map<String, String> payloadClaims;

    private JWTDecoder(String jwt) {
        String[] parts = split(jwt);
        String headerJson = new String(Base64.getDecoder().decode(parts[0]), StandardCharsets.UTF_8);
        String payloadJson = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);

        try {
            // TODO: use json-parser instead of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            headerClaims = objectMapper.readValue(headerJson, Map.class);
            payloadClaims = objectMapper.readValue(payloadJson, Map.class);
        } catch (IOException e) {
            throw new JWTDecodeException("Could not parse JWT", e);
        }
    }

    private String[] split(String jwt) {
        String[] parts = jwt.split("\\.");
        if (parts.length == 2 && jwt.endsWith(".")) {
            return new String[]{parts[0], parts[1], ""};
        }
        if (parts.length != 3) {
            throw new JWTDecodeException("Illegal format");
        }
        return parts;
    }

    public static JWT decode(String jwt) {
        return new JWTDecoder(jwt);
    }

    @Override
    public String getName() {
        return payloadClaims.get(Claim.NAME);
    }
}
