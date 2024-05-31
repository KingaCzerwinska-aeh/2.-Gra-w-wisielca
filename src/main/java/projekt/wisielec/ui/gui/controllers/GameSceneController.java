package projekt.wisielec.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import projekt.wisielec.game.Game;
import projekt.wisielec.game.Status;
import projekt.wisielec.ui.gui.GuiGame;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static projekt.wisielec.ui.gui.controllers.SceneChanger.changeScene;

public class GameSceneController implements Initializable {
    @FXML
    public VBox buttons;

    @FXML
    private ImageView imageOfHangman;

    @FXML
    private Label guessedLetters;

    private int scorePlayer1 = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessedLetters.setText(Game.getInstance().getHangman().getGuessedLetters());
        setNeededButtons();
    }

    private void setNeededButtons() {
        buttons.setVisible(true);

    }

    public void buttonClicked(ActionEvent event) throws IOException {
        final Game game = Game.getInstance();

        char letter = ((Button) event.getSource()).getText().toLowerCase().charAt(0);
        game.getHangman().checkLetter(letter);
        ((Button) event.getSource()).setDisable(true);
        updateScene();

        if (game.getHangman().isEnd()) {
            endGameOccurrence(event, game);
        }
    }

    private void endGameOccurrence(ActionEvent event, Game game) throws IOException {
        StringBuilder message = makeEndMessage(game);
        Alert alert = endGameAlert(message);
        ButtonType buttonPlayAgain = new ButtonType("Graj dalej!");
        ButtonType buttonBackToHome = new ButtonType("Powrót");
        alert.getButtonTypes().setAll(buttonPlayAgain, buttonBackToHome);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonPlayAgain) {
            prepareNewGame(game);
        } else {
            cleanAndBackHome(event);
        }
    }

    private void cleanAndBackHome(ActionEvent event) throws IOException {
        scorePlayer1 = 0;
        Game.resetGame();
        changeScene(event, "homeScene.fxml");
    }

    private StringBuilder makeEndMessage(Game game) {
        StringBuilder message = new StringBuilder();
        if (game.getHangman().getStatus().equals(Status.GUESSED)) {
            updateScores();
            message.append("Zgadłeś: ");
        } else {
            message.append("Nie zgadłeś: ");
        }
        message.append(game.getHangman().getWord());


            message.append("\nWynik: ").append(scorePlayer1);

        return message;
    }

    private Alert endGameAlert(StringBuilder message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("KONIEC");
        alert.setHeaderText(null);
        alert.setContentText(message + "\nChcesz jeszcze raz?");
        return alert;
    }

    private void prepareNewGame(Game game) {
        game.changeWordForHangman();
        unlockButtons();
        updateScene();
    }

    private void unlockButtons() {
            for (Node child : buttons.getChildren()) {
                for (Node node : ((HBox) child).getChildren()) {
                    node.setDisable(false);
                }
            }

    }

    private void updateScores() {
        scorePlayer1++;
    }

    private void updateScene() {
        final Game game = Game.getInstance();
        changeImage(game.getHangman().getStatus());
        guessedLetters.setText(game.getHangman().getGuessedLetters());
    }

    private void changeImage(Status status) {
        if (Status.GUESSED.equals(status)) {
            return;
        }
        String name = status.ordinal() + ".png";
        final URL resource = Objects.requireNonNull(GuiGame.class.getClassLoader().getResource(name), name + " resource not found!");
        imageOfHangman.setImage(new Image(resource.toString()));
    }
}
