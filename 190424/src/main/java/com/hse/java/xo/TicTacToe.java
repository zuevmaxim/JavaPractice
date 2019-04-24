package com.hse.java.xo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;



public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final int N = 3;
    private Button[][] buttons = new Button[N][N];
    private Viewer viewer = new Viewer();
    private Logic logic = new Logic(viewer);
    private Label label;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int ci = i;
                int cj = j;
                buttons[i][j] = new Button();
                buttons[i][j].setOnAction(result -> logic.pushedButton(ci, cj));
                root.add(buttons[i][j], i, j);
            }
        }

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        var column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        var row1 = new RowConstraints();
        row1.setPercentHeight(30);
        var row2 = new RowConstraints();
        row2.setPercentHeight(30);
        var row3 = new RowConstraints();
        row3.setPercentHeight(30);
        root.getColumnConstraints().addAll(column1, column2);
        root.getRowConstraints().addAll(row1, row2, row3);

        label = new Label("");

        root.add(label, 0, N, N / 2, 1);
        var endGameButton = new Button("End game.");
        endGameButton.setOnAction(result -> Platform.exit());
        root.add(endGameButton, N / 2, N);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class Viewer {

        public void showResult(XO result) {
            String text = "Draw";
            switch (result) {
                case X: text = "X wins!";
                break;
                case O: text = "O wins!";
                break;
            }
            label.setText(text);
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    buttons[i][j].setDisable(true);
                }
            }
        }

        public void set(int x, int y, XO symbol) {
            buttons[x][y].setText(symbol.name());
        }
    }
}