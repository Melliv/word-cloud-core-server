package com.example.wordcloudcoreserver;

import com.example.wordcloudcoreserver.enums.WordCloudStatus;
import com.example.wordcloudcoreserver.models.TextFile;
import com.example.wordcloudcoreserver.models.Word;
import com.example.wordcloudcoreserver.models.Words;
import com.example.wordcloudcoreserver.repositories.TextFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TextFileRepository textFileRepository;

    public void run(ApplicationArguments args) {
        Words words = new Words();
        List<Word> wordItems = new ArrayList<>();
        wordItems.add(new Word(null, "test", 6));
        wordItems.add(new Word(null, "bar", 5));
        wordItems.add(new Word(null, "foo", 3));
        wordItems.add(new Word(null, "testing", 3));
        wordItems.add(new Word(null, "some1", 1));
        words.setItems(wordItems);
        words.setCount(18);
        words.setProcessingTime(0.1F);

        TextFile textFile = new TextFile();
        textFile.setName("example.txt");
        textFile.setSize(10L);
        textFile.setStatus(WordCloudStatus.COMPLETE);
        textFile.setWords(words);

        textFileRepository.save(textFile);
    }
}