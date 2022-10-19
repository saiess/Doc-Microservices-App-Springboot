package com.microserviceDoc.doc.services.impl;

import com.microserviceDoc.doc.daos.DocDAO;
import com.microserviceDoc.doc.dtos.DocDTO;
import com.microserviceDoc.doc.exceptions.UserNotAllowedException;
import com.microserviceDoc.doc.models.DocModel;
import com.microserviceDoc.doc.services.DocService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    DocDAO docDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createDocument(DocDTO docDTO) {

        checkNotNull(docDTO.getContent());
        checkNotNull(docDTO.getTitle());
        checkNotNull(docDTO.getUserId());

        DocModel docModel = modelMapper.map(docDTO, DocModel.class);

        if(isNull(docModel.getAvailable())) {
            docModel.setAvailable(false);
        }

        docDAO.save(docModel);

        modelMapper.map(docModel, docDTO);
    }

    @Override
    public List<DocDTO> getDocsForUserId(String userId) {
        final List<DocModel> allByUserId = docDAO.findAllByUserIdOrderByUpdatedAtDesc(userId);

        return allByUserId.stream()
                .map(docModel -> modelMapper.map(docModel, DocDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocDTO getDoc(String docId) {
        final Optional<DocModel> optionalDocModel = docDAO.findById(docId);
        if(optionalDocModel.isPresent()) {
            return modelMapper.map(optionalDocModel, DocDTO.class);
        }

        return null;
    }

    @Override
    public List<DocDTO> getTopRecentDocs() {
        final Page<DocModel> recentDocs = docDAO.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,  "updatedAt")));

        return recentDocs.stream()
                .map(docModel -> modelMapper.map(docModel, DocDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateDoc(DocDTO docDTO, String userId) throws UserNotAllowedException {
        final Optional<DocModel> optionalDocModel = docDAO.findById(docDTO.getId());
        if (optionalDocModel.isPresent()) {

            final DocModel docModel = optionalDocModel.get();
            if (docModel.getUserId().equals(userId)) {

                modelMapper.map(docDTO, docModel);
                docDAO.save(docModel);

                modelMapper.map(docModel, docDTO);
                return;
            } else {
                throw new UserNotAllowedException("you're not allowed tp modify this document");
            }
        }

        throw new NoSuchElementException("no document with id " + docDTO.getId() + "was found");
    }
}
