package com.microserviceDoc.doc.dtos;

import lombok.Data;
import java.util.Date;

@Data
public class DocDTO {
    private String id;
    private  String content;
    private String userId;
    private String title;
    private Boolean available;
    private Date createdAt;
    private Date updatedAt;
}
