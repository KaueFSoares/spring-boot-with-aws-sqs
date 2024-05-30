package kauesoares.sws.sqs.project.repository;

import kauesoares.sws.sqs.project.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String>{
}
