package academy.kata.mis.kafkatest.util;

import academy.kata.mis.kafkatest.model.FileTransferDTO;

import academy.kata.mis.kafkatest.model.enums.DocumentDestination;
import academy.kata.mis.kafkatest.model.enums.DocumentRole;
import academy.kata.mis.kafkatest.model.enums.DocumentType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.hc.client5.http.utils.Base64;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.UUID;

public class CustomFileTransferDTOJsonSerializer extends JsonSerializer<FileTransferDTO> {

    protected final ObjectMapper objectMapper;
    protected boolean addTypeInfo;
    private ObjectWriter writer;
    protected Jackson2JavaTypeMapper typeMapper;
    private boolean typeMapperExplicitlySet;

    public CustomFileTransferDTOJsonSerializer() {
        this((JavaType) null, JacksonUtils.enhancedObjectMapper());
    }

    public CustomFileTransferDTOJsonSerializer(TypeReference<? super FileTransferDTO> targetType) {
        this(targetType, JacksonUtils.enhancedObjectMapper());
    }

    public CustomFileTransferDTOJsonSerializer(ObjectMapper objectMapper) {
        this((JavaType) null, objectMapper);
    }

    public CustomFileTransferDTOJsonSerializer(TypeReference<? super FileTransferDTO> targetType, ObjectMapper objectMapper) {
        this(targetType == null ? null : objectMapper.constructType(targetType.getType()), objectMapper);
    }

    public CustomFileTransferDTOJsonSerializer(JavaType targetType, ObjectMapper objectMapper) {
        this.addTypeInfo = true;
        this.typeMapper = new DefaultJackson2JavaTypeMapper();
        this.typeMapperExplicitlySet = false;
        Assert.notNull(objectMapper, "'objectMapper' must not be null.");
        this.objectMapper = objectMapper;
        this.writer = objectMapper.writerFor(targetType);
    }


    public byte[] serialize(String topic, @Nullable academy.kata.mis.kafkatest.model.FileTransferDTO data) {
        academy.kata.mis.kafkatest.util.CustomFileTransferDTOJsonSerializer.FileTransferDTO send = new FileTransferDTO(
                data.operationId(),
                data.userId(),
                data.documentType(),
                data.destination(),
                data.role(),
                Base64.encodeBase64String(data.body()));

        if (data == null) {
            return null;
        } else {
            try {

                return this.writer.writeValueAsBytes(send);
            } catch (IOException var4) {
                IOException ex = var4;
                throw new SerializationException("Can't serialize data [" + data + "] for topic [" + topic + "]", ex);
            }
        }
    }

    private static record FileTransferDTO(UUID operationId,
                                          UUID userId,
                                          DocumentType documentType,
                                          DocumentDestination destination,
                                          DocumentRole role,
                                          String body) {
    }
}
