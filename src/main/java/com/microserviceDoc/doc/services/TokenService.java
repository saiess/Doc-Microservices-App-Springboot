package com.microserviceDoc.doc.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenService {

    String getUserId(String jwtToken);

    List<String> getUserRoles(String jwtToken);
}
