package com.microserviceDoc.doc.services;

import com.microserviceDoc.doc.dtos.DocDTO;
import com.microserviceDoc.doc.exceptions.UserNotAllowedException;

import java.util.List;

public interface DocService {
    void createDocument(DocDTO docDTO);

    List<DocDTO> getDocsForUserId(String userId);

    DocDTO getDoc(String docId);

    List<DocDTO> getTopRecentDocs();

    void updateDoc(DocDTO docDTO, String userId) throws UserNotAllowedException;
}
