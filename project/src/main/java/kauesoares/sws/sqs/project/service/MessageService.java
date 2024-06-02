package kauesoares.sws.sqs.project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kauesoares.sws.sqs.project.model.Message;
import kauesoares.sws.sqs.project.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final QueueProducerService queueProducerService;

    private final MessageRepository messageRepository;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendMessage(HttpHeaders headers, String body) {
        Message message = Message.builder()
                .headers(headers.toSingleValueMap())
                .body(this.convertJsonToMap(body))
                .build();

        UUID queueMessageId = this.queueProducerService.publishMessage(message);

        message.setQueueMessageId(queueMessageId);

        this.messageRepository.save(message);

    }

    public Map<String, Object> convertJsonToMap(String jsonString) throws IOException {
        return this.objectMapper.readValue(jsonString, new TypeReference<>() {
        });
    }

}
