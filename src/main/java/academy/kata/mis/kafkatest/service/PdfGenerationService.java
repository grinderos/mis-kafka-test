package academy.kata.mis.kafkatest.service;

import academy.kata.mis.kafkatest.model.enums.DocumentDestination;
import academy.kata.mis.kafkatest.model.enums.DocumentRole;
import academy.kata.mis.kafkatest.model.enums.DocumentType;

import java.io.IOException;
import java.util.UUID;

public interface PdfGenerationService {
    byte[] generatePdf(String text) throws IOException;

    String generateInfo(UUID operationId,
                        UUID userId,
                        DocumentType documentType,
                        DocumentDestination destination,
                        DocumentRole role);
}
