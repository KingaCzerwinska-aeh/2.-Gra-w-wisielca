package projekt.wisielec;

import javafx.application.Application;
import projekt.wisielec.ui.gui.GuiGame;

import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Application.launch(GuiGame.class);
        sc.close();
    }
}