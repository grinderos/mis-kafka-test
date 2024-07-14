package academy.kata.mis.kafkatest.service;

import academy.kata.mis.kafkatest.model.DocumentInfoDTO;

import java.util.List;

public interface ObjectKeeper {
    List<DocumentInfoDTO> getDocumentInfo();
    void setDocumentInfo(DocumentInfoDTO documentId);

}
