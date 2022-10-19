package com.microserviceDoc.doc.services;


import java.util.List;


public interface TokenService {

    String getUserId(String jwtToken);

    List<String> getUserRoles(String jwtToken);
}
