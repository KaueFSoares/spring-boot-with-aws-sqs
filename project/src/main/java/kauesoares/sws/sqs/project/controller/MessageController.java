package kauesoares.sws.sqs.project.controller;

import kauesoares.sws.sqs.project.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(
            @RequestHeader
            HttpHeaders headers,

            @RequestBody
            String body
    ) {
        this.messageService.sendMessage(headers, body);
        return ResponseEntity.noContent().build();
    }

}
