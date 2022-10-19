package com.microserviceDoc.doc.services.impl;

import com.microserviceDoc.doc.services.TokenService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getUserId(String jwtToken) {
        //abc.123.awe
        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        return claimsJson.getString("iss");
    }

    @Override
    public List<String> getUserRoles(String jwtToken) {
        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        // "[admin, user]"
        String audience = claimsJson.getString("aud");

        final String[] split = audience
                .replace("[", "")
                .replace("]", "")
                .split(",");
        return Stream.of(split).map(String::trim).collect(Collectors.toList());
    }
}
