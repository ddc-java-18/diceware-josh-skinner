package edu.cnm.deepdive.diceware.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class WordListResourceProvider implements WordsProvider {

  private final Collection<String> words;

  @Autowired
  public WordListResourceProvider(@Value("${diceware.word-list}") String wordlistResourcePath) {
    Resource resource = new ClassPathResource(wordlistResourcePath);
    try (Stream<String> lines = Files.lines(Paths.get(resource.getURI()))) {
      words = lines
          .map(String::strip)
          .filter((line) -> !line.isEmpty())
          .toList();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Collection<String> words() {
    return words;
  }
}
