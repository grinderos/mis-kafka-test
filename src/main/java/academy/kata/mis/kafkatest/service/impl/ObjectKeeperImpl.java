package academy.kata.mis.kafkatest.service.impl;

import academy.kata.mis.kafkatest.model.DocumentInfoDTO;
import academy.kata.mis.kafkatest.service.ObjectKeeper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectKeeperImpl implements ObjectKeeper {
    static List<DocumentInfoDTO> documentInfoDTO = new ArrayList<>();

    @Override
    public List<DocumentInfoDTO> getDocumentInfo() {
        return documentInfoDTO;
    }

    @Override
    public void setDocumentInfo(DocumentInfoDTO documentInfo) {
        this.documentInfoDTO.add(documentInfo);
    }
}
