package kauesoares.sws.sqs.project.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Message {

    @Id
    private String id;

    private Map<String, String> headers;

    private String body;

    private String queueMessageId;

    @CreatedDate
    private LocalDateTime createdAt;

}
