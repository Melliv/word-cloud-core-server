package com.example.wordcloudcoreserver.services;

import com.example.wordcloudcoreserver.Constants;
import com.example.wordcloudcoreserver.dto.TextFilesPagination;
import com.example.wordcloudcoreserver.enums.WordCloudStatus;
import com.example.wordcloudcoreserver.models.TextFile;
import com.example.wordcloudcoreserver.repositories.TextFileRepository;
import com.example.wordcloudcoreserver.websockets.SocketService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TextFileService {

    @Autowired
    private TextFileRepository textFileRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SocketService socketService;


    public void uploadTextFile(MultipartFile file) throws IOException {
        TextFile textFile = new TextFile();
        textFile.setName(file.getOriginalFilename());
        textFile.setSize(file.getSize());
        textFile.setStatus(WordCloudStatus.PENDING);
        textFileRepository.save(textFile);

        Message message = MessageBuilder.withBody(file.getBytes())
                .setHeader("ContentType", file.getContentType())
                .setHeader("TextFileID", textFile.getId())
                .build();
        rabbitTemplate.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, message);
    }


    public TextFilesPagination getTextFiles(Integer limit, Integer offset) throws Exception {
        if (limit < 0) throw new Exception("Limit must be >= 0");
        if (offset < 0) throw new Exception("Offset must be >= 0");

        return new TextFilesPagination(
                textFileRepository.getTextFiles(PageRequest.of(offset, limit)),
                textFileRepository.getTextFilesCount()
        );
    }

    public TextFile getTextFile(Long id) {
        return textFileRepository.findById(id).orElse(null);
    }

    public void temp() {
        System.out.println("Testing socket.....123");
        socketService.sendMessage("messages", "updateData");
    }

}