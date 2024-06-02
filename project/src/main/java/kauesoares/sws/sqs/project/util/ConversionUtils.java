package kauesoares.sws.sqs.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kauesoares.sws.sqs.project.model.Message;

import java.io.IOException;
import java.util.Map;

public class ConversionUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> convertJsonToMap(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, new TypeReference<>() {
        });
    }

    public static String convertObjectToJson(Message message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }
}
