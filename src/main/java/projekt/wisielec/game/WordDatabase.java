package projekt.wisielec.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WordDatabase {
    private static WordDatabase instance = new WordDatabase();

//    private static final String PATH_WORDS = "Easy.txt";

    private List<String> words;

    private WordDatabase() {
        if (instance != null) {
            throw new IllegalStateException("Game already constructed");
        }
    }

    public static WordDatabase getInstance() {
        if (instance == null) {
            instance = new WordDatabase();
        }
        return instance;
    }

    public void init() {
        downloadWordsFromFile("Easy.txt");
    }
    public void init(String mode) {
        downloadWordsFromFile(mode);
    }

    private void downloadWordsFromFile(String pathWords) {
        final URL resource = WordDatabase.class.getClassLoader().getResource(pathWords);
        try {
            final Path path = Paths.get(resource.toURI());
            words = readWordsFromFile(path);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private List<String> readWordsFromFile(Path path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<String> getWords() {
        return words;
    }

}
