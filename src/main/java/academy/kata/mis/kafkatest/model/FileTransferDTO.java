package academy.kata.mis.kafkatest.model;

import academy.kata.mis.kafkatest.model.enums.DocumentDestination;
import academy.kata.mis.kafkatest.model.enums.DocumentRole;
import academy.kata.mis.kafkatest.model.enums.DocumentType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FileTransferDTO(UUID operationId,
                              UUID userId,
                              DocumentType documentType,
                              DocumentDestination destination,
                              DocumentRole role,
                              byte[] body) {
}
