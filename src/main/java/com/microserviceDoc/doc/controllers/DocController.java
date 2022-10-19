package com.microserviceDoc.doc.controllers;

import com.microserviceDoc.doc.dtos.DocDTO;
import com.microserviceDoc.doc.exceptions.UserNotAllowedException;
import com.microserviceDoc.doc.services.DocService;
import com.microserviceDoc.doc.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    DocService docService;
    @Autowired
    TokenService tokenService;

    //        Create his own docs
    @PostMapping("/create")
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {
        //TODO:  service that handles creation logic
        docService.createDocument(docDTO);
        return docDTO;
    }


    //        fetch his own docs
    @GetMapping("/{userId}/all")
    public List<DocDTO> getUserDocs(@PathVariable String userId) {
        //TODO:  service that handles fetch docs logic
        return docService.getDocsForUserId(userId);
    }


    //        Fetch a public doc
    @GetMapping("/{docId}")
    public DocDTO getDoc(@PathVariable String docId) {
        //TODO:  service that handles fetch docs logic
        return docService.getDoc(docId);
    }
    //        Fetch 10 most recent public docs
    @GetMapping("/recent")
    public List<DocDTO> getRecentDoc() {
        //TODO:  service that handles fetch docs logic
        return docService.getTopRecentDocs();
    }
    //        Update his own docs
    @PutMapping("/update")
    public DocDTO updateDoc(@RequestBody DocDTO docDTO, HttpServletRequest httpServletRequest) throws UserNotAllowedException {

        String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
        String jwtToken = StringUtils.removeStart(tokenHeader,  "Bearer ").trim();
        //TODO: extract userId from token
        String userId = tokenService.getUserId(jwtToken); //userId -> issuer on jwtToken

        //TODO:  service that handles fetch docs logic
        docService.updateDoc(docDTO, userId);
        return docDTO;
    }


//    //        Delete his own docs

}
