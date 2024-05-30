package kauesoares.sws.sqs.project.service;

import kauesoares.sws.sqs.project.model.Message;
import kauesoares.sws.sqs.project.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final QueuePublisherService queuePublisherService;

    private final MessageRepository messageRepository;

    public void sendMessage(HttpHeaders headers, String body) {
        Message message = Message.builder()
                .headers(headers.toSingleValueMap())
                .body(body)
                .build();

        String queueMessageId = this.queuePublisherService.publishMessage(message);

        message.setQueueMessageId(queueMessageId);

        this.messageRepository.save(message);

    }

}
