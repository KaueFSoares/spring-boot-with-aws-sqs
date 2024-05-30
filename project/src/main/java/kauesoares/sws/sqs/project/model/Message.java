package kauesoares.sws.sqs.project.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;

@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Message {

    @Id
    private String id;

    private HttpHeaders headers;

    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

}
