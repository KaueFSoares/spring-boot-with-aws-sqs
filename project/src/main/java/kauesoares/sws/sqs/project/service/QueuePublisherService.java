package kauesoares.sws.sqs.project.service;

import com.amazonaws.services.sqs.AmazonSQS;
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

    private final AmazonSQS amazonSQS;

    private final AWSSQSProperties awssqsProperties;

    private final ObjectMapper objectMapper;

    public String publishMessage(Message message) {
        try {
            SendMessageResult result = this.amazonSQS.sendMessage(this.awssqsProperties.getUrl(), this.objectMapper.writeValueAsString(message));

            return result.getMessageId();
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
            return null;
        }
    }

}
