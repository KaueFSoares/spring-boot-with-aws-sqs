package kauesoares.sws.sqs.project.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import kauesoares.sws.sqs.project.config.properties.ExternalServiceProperties;
import kauesoares.sws.sqs.project.model.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@Log4j2
public class QueueConsumerService {

    private final RestClient restClient;

    public QueueConsumerService(
            ExternalServiceProperties externalServiceProperties,
            RestClient.Builder restClientBuilder
    ) {
        this.restClient = restClientBuilder
                .baseUrl(externalServiceProperties.getUrl())
                .build();
    }

    @SqsListener(
            queueNames = "${aws.sqs.name}",
            maxConcurrentMessages = "10",
            maxMessagesPerPoll = "10"
    )
    public void consume(Message message) throws InterruptedException {
        this.sendHttpRequest(message);
    }

    public void sendHttpRequest(Message message) {
        ResponseEntity<String> response = restClient.post()
                .headers(headers -> headers.add(HttpHeaders.CONTENT_TYPE, "application/json"))
                .body(message)
                .retrieve()
                .toEntity(String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Request successful for message with id: {}", message.getId());
            return;
        }

        log.error("Request failed for message with id: {}", message.getId());
        log.error("Response status: {}", response.getStatusCode());
        log.error("Response body: {}", response.getBody());

        throw new RuntimeException("Request failed for message with id: " + message.getId() + ". Check logs for more details.");
    }

}
