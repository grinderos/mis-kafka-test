package academy.kata.mis.kafkatest.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DocumentInfoDTO(UUID operationId,
                              UUID documentId,
                              LocalDateTime creationDate,
                              LocalDateTime expirationDate) {
}
