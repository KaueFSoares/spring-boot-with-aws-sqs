package kauesoares.sws.sqs.project.service;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsOperations;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import kauesoares.sws.sqs.project.config.properties.AWSSQSProperties;
import kauesoares.sws.sqs.project.model.Message;
import kauesoares.sws.sqs.project.util.ConversionUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.UUID;

@Service
@Log4j2
public class QueueProducerService {

    private final SqsOperations sqsTemplate;

    public QueueProducerService(SqsAsyncClient sqsClient, AWSSQSProperties awssqsProperties) {
        this.sqsTemplate = SqsTemplate.builder()
                .sqsAsyncClient(sqsClient)
                .configure(options -> options
                        .defaultQueue(awssqsProperties.getName())
                )
                .buildSyncTemplate();
    }

    public UUID publishMessage(Message message) {
        try {
            String messageJson = ConversionUtils.convertObjectToJson(message);

            log.info("Publishing message to SQS: {}", messageJson);

            SendResult<Message> result = this.sqsTemplate.send(message);

            return result.messageId();
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
            return null;
        }
    }

}
