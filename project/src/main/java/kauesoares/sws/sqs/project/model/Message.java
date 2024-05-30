package kauesoares.sws.sqs.project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @Id
    private String id;

    @JsonProperty("header")
    private Map<String, String> headers;

    private Map<String, Object> body;

    private String queueMessageId;

    @CreatedDate
    private LocalDateTime createdAt;

}
