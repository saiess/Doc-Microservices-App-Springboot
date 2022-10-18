package com.microserviceDoc.doc.controllers;

import com.microserviceDoc.doc.dtos.DocDTO;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    //        Create his own docs
    @PostMapping("/create")
    public DocDTO createDocument(@RequestBody DocDTO docDTO) {
        //TODO:  service that handles creation logic
        return null;
    }


    //        fetch his own docs
    @GetMapping("/{userId}/all")
    public List<DocDTO> getUserDocs(@PathVariable String userId) {
        //TODO:  service that handles fetch docs logic
        return null;
    }


    //        Fetch a public doc
    @GetMapping("/{docId}")
    public DocDTO getDoc(@PathVariable String docId) {
        //TODO:  service that handles fetch docs logic
        return null;
    }
    //        Fetch 10 most recent public docs
    @GetMapping("/recent")
    public List<DocDTO> getRecentDoc() {
        //TODO:  service that handles fetch docs logic
        return null;
    }
    //        Update his own docs
    @PutMapping("/update/{docId}")
    public DocDTO updateDoc(@RequestBody DocDTO docDTO) {
        //TODO:  service that handles fetch docs logic
        return null;
    }
//    //        Delete his own docs
//    @DeleteMapping("/delete")
//    public DocDTO deleteDoc(@RequestBody DocDTO docDTO) {
//        //TODO:  service that handles fetch docs logic
//        return null;
//    }
}
