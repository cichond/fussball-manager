package io.codeclubkrk.fm.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codeclubkrk.fm.jwt.exceptions.JWTEncodeException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author darek
 * @since 27.12.2018
 */
public final class JWTEncoder {

    private final String header;
    private final String payload;

    private JWTEncoder(Map<String, String> headerClaims, Map<String, String> payloadClaims) {
        try {
            // TODO: use json-parser instead of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            this.header = objectMapper.writeValueAsString(headerClaims);
            this.payload = objectMapper.writeValueAsString(payloadClaims);
        } catch (JsonProcessingException e) {
            throw new JWTEncodeException("Could not write claims as JSON", e);
        }
    }

    private String sign() {
        Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();

        String header = base64Encoder.encodeToString(this.header.getBytes(StandardCharsets.UTF_8));
        String payload = base64Encoder.encodeToString(this.payload.getBytes(StandardCharsets.UTF_8));

        return String.format("%s.%s.", header, payload);
    }

    public static Builder create() {
        return new Builder();
    }

    private static final class Builder {
        private final Map<String, String> headerClaims;
        private final Map<String, String> payloadClaims;

        private Builder() {
            headerClaims = new HashMap<>();
            headerClaims.put(Claim.ALGORITHM, "none");
            payloadClaims = new HashMap<>();
        }

        public Builder withName(String name) {
            putClaim(Claim.NAME, name);
            return this;
        }

        public String build() {
            return new JWTEncoder(headerClaims, payloadClaims).sign();
        }

        private void putClaim(String name, String value) {
            payloadClaims.put(name, value);
        }
    }
}
