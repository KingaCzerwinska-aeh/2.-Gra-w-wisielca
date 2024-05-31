package projekt.wisielec.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import projekt.wisielec.game.Game;
import projekt.wisielec.game.WordDatabase;

import java.net.URISyntaxException;
import java.net.URL;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static projekt.wisielec.ui.gui.controllers.SceneChanger.changeScene;

public class HomeController implements Initializable {
    Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       WordDatabase.getInstance().init();
    }

    @FXML
    public void startOnePlayerGame(ActionEvent event) throws IOException {
        game = Game.getInstance();
        game.changeWordForHangman();
        changeScene(event, "DifficultyLevelScene.fxml");
    }
    public void EasyGame(ActionEvent event) throws IOException {
        WordDatabase.getInstance().init("Easy.txt");
        game = Game.getInstance();
        game.changeWordForHangman();
        changeScene(event, "gameScene.fxml");

    }
    public void MediumGame(ActionEvent event) throws IOException {
        WordDatabase.getInstance().init("Medium.txt");
        game = Game.getInstance();
        game.changeWordForHangman();
        changeScene(event, "gameScene.fxml");
    }
    public void HardGame(ActionEvent event) throws IOException {
        WordDatabase.getInstance().init("Hard.txt");
        game = Game.getInstance();
        game.changeWordForHangman();
        changeScene(event, "gameScene.fxml");
    }
    public void addEdit(ActionEvent event) throws IOException, URISyntaxException {
        game = Game.getInstance();
        game.changeWordForHangman();
        changeScene(event, "AddEditDifficultyLevel.fxml");
    }

    public void addEditEasy(ActionEvent event) throws IOException, URISyntaxException {
        final URL resource = HomeController.class.getClassLoader().getResource("Easy.txt");
        final Path path = Paths.get(resource.toURI());
        java.awt.Desktop.getDesktop().open(path.toFile());

    }
    public void addEditMedium(ActionEvent event) throws IOException, URISyntaxException {
        final URL resource = HomeController.class.getClassLoader().getResource("Medium.txt");
        final Path path = Paths.get(resource.toURI());
        java.awt.Desktop.getDesktop().open(path.toFile());
    }
    public void addEditHard(ActionEvent event) throws IOException, URISyntaxException {
        final URL resource = HomeController.class.getClassLoader().getResource("Hard.txt");
        final Path path = Paths.get(resource.toURI());
//        InputStream inputStream = getClass().getResourceAsStream("/Easy.txt");
        java.awt.Desktop.getDesktop().open(path.toFile());
    }
}
