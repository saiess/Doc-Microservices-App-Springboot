package com.microserviceDoc.doc.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

import static java.util.Objects.isNull;

@Data
@Document(collection = "docs")
public class DocModel implements Serializable, Persistable<String> {

    @Id
    private String id;
    private  String content;
    private String userId;
    private String title;
    private Boolean available;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @Override
    public boolean isNew() {
        return isNull(this.createdAt);
    }
}
