package kauesoares.sws.sqs.project.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import kauesoares.sws.sqs.project.config.properties.AWSSQSProperties;
import kauesoares.sws.sqs.project.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class QueuePublisherService {

    private final AmazonSQSAsync amazonSQSAsync;

    private final AWSSQSProperties awssqsProperties;

    private final ObjectMapper objectMapper;

    public String publishMessage(Message message) {
        try {
            String messageJson = this.objectMapper.writeValueAsString(message);

            log.info("Publishing message to SQS: {}", messageJson);

            SendMessageResult result = this.amazonSQSAsync.sendMessage(this.awssqsProperties.getUrl(), messageJson);

            return result.getMessageId();
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
            return null;
        }
    }

}
